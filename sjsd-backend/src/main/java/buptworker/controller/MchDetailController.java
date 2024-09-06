package buptworker.controller;
/*
合并时需要修改：
1.api接口，我怕冲突没把我最后改的接口推上去，可能有重复的端口
2.id改成从cookie获得
3.如果要运行在别的电脑需要修改图片存储的路径和WebConfig配置

  */
import buptworker.entity.Merchant;
import buptworker.service.MchDetailService;
import buptworker.service.FileUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import buptworker.util.SessionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class MchDetailController {

    @Autowired
    private MchDetailService mchdetailService;

    @Autowired
    private FileUploadService fileUploadService;  // 注入文件上传服务

    // 定义一个固定的 merchant_id
    private int defaultMerchantID = 5;  // 修改为cookie！！！
    private SessionUtil cookie;

    // 获取商家信息 API
    @GetMapping("/merchants")
    public ResponseEntity<Merchant> getMerchant(HttpServletRequest request) {
        defaultMerchantID = cookie.getUserID(request).intValue();
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
    public ResponseEntity<Map<String, Object>> updateMerchant(HttpServletRequest request,
            @RequestBody Merchant merchant) {
        // 直接使用默认的 merchantID
        defaultMerchantID = cookie.getUserID(request).intValue();
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
    public ResponseEntity<Map<String, Object>> uploadAvatar(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
        defaultMerchantID = cookie.getUserID(request).intValue();
        Map<String, Object> response = new HashMap<>();
        try {
            // 使用 FileUploadService 上传文件
            String imageUrl = fileUploadService.uploadFile(file);

            // 更新商家头像 URL
            boolean updated = mchdetailService.updateMerchantAvatar(defaultMerchantID, imageUrl);

            if (updated) {
                response.put("message", "图片上传成功");
                response.put("avatarURL", imageUrl);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "图片上传失败");
                return ResponseEntity.status(500).body(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
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