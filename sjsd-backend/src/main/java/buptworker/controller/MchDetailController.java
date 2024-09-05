package buptworker.controller;
/*
合并时需要修改：
2.id改成从cookie获得
3.如果要运行在别的电脑需要修改图片存储的路径和WebConfig配置

  */
import buptworker.entity.Merchant;
import buptworker.service.MchDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MchDetailController {

    @Autowired
    private MchDetailService mchdetailService;

    // 定义一个固定的 merchant_id
    private final int defaultMerchantID = 5;  // 修改为cookie！！！

    // 获取商家信息 API
    @GetMapping("/merchants")
    public ResponseEntity<Merchant> getMerchant() {
        // 使用默认的 merchantID 获取商家信息
        Merchant merchant = mchdetailService.getMerchantById(defaultMerchantID);
        if (merchant != null) {
            return ResponseEntity.ok(merchant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 更新商家信息 API
    @PutMapping("/merchants")
    public ResponseEntity<Map<String, Object>> updateMerchant(
            @RequestBody Merchant merchant) {
        // 直接使用默认的 merchantID
        merchant.setMerchantID(defaultMerchantID);
        boolean updated = mchdetailService.updateMerchant(merchant);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("message", "商家信息更新成功");
            response.put("merchant_id", defaultMerchantID);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "商家信息更新失败");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 后端Controller：上传商家头像 API
    @PostMapping("/merchants/upload-avatar")
    public ResponseEntity<Map<String, Object>> uploadAvatar(@RequestParam("file") MultipartFile file) {
        String uploadDir = "D:/Desktop/image/images";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        try {
            // 保存图片到本地目录
            Files.write(filePath, file.getBytes());

            // 生成图片的 URL，可以通过前端访问
            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/")
                    .path(fileName)
                    .toUriString();

            // 更新商家头像 URL
            boolean updated = mchdetailService.updateMerchantAvatar(defaultMerchantID, imageUrl);

            Map<String, Object> response = new HashMap<>();
            if (updated) {
                response.put("message", "图片上传成功");
                response.put("avatarURL", imageUrl); // 返回生成的头像URL
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "图片上传失败");
                return ResponseEntity.status(500).body(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "图片上传失败");
            return ResponseEntity.status(500).body(response);
        }
    }



    // 获取商家类型 API
    @GetMapping("/merchant-types")
    public ResponseEntity<List<String>> getMerchantTypes() {
        List<String> types = mchdetailService.getMerchantTypes();
        return ResponseEntity.ok(types);
    }
}