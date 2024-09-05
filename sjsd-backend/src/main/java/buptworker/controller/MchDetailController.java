package buptworker.controller;

import buptworker.entity.Merchant;
import buptworker.service.MchDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // 更新商家信息 API
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
        // 假设图片存储后返回图片的URL
        String imageURL = "https://example.com/images/avatar.jpg";  // 图片上传逻辑省略
        boolean updated = mchdetailService.updateMerchantAvatar(merchantID, imageURL);
        Map<String, Object> response = new HashMap<>();
        if (updated) {
            response.put("message", "图片上传成功");
            response.put("image_url", imageURL);
            return ResponseEntity.ok(response);
        } else {
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
