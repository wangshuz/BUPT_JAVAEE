package buptworker.controller;

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

    // 获取商家信息 API
    @GetMapping("/merchants/{merchant_id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable("merchant_id") int merchantID) {
       Merchant merchant = mchdetailService.getMerchantById(merchantID);
        if (merchant != null) {
            return ResponseEntity.ok(merchant);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // 更新商家信息 APIf
    @PutMapping("/merchants/{merchant_id}")
    public ResponseEntity<Map<String, Object>> updateMerchant(
            @PathVariable("merchant_id") int merchantID,
            @RequestBody Merchant merchant) {
        merchant.setMerchantID(merchantID);
        boolean updated = mchdetailService.updateMerchant(merchant);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("message", "商家信息更新成功");
            response.put("merchant_id", merchantID);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "商家信息更新失败");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 上传商家头像 API
    @PostMapping("/merchants/{merchant_id}/upload-avatar")
    public ResponseEntity<Map<String, Object>> uploadAvatar(
            @PathVariable("merchant_id") int merchantID,
            @RequestParam("file") MultipartFile file) {

        // 创建文件存储目录
        String uploadDir = "D:/Desktop/image/images"; // 替换为你的上传目录
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // 创建目录
        }

        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        try {
            // 保存文件
            Files.write(filePath, file.getBytes());

            // 构建图片的 URL
            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/") // 替换为你的图片访问路径
                    .path(fileName)
                    .toUriString();

            // 更新商家头像的 URL
            boolean updated = mchdetailService.updateMerchantAvatar(merchantID, imageUrl);

            Map<String, Object> response = new HashMap<>();
            if (updated) {
                response.put("message", "图片上传成功");
                response.put("image_url", imageUrl);
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
