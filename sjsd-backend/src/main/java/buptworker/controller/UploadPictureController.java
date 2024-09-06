package buptworker.controller;
import buptworker.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/other")
public class UploadPictureController {

    @Autowired
    private FileUploadService fileUploadService;  // 注入文件上传服务

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 使用 FileUploadService 上传文件
            String imageUrl = fileUploadService.uploadFile(file);

            response.put("message", "文件上传成功");
            response.put("fileURL", imageUrl);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            response.put("message", "文件上传失败");
            return ResponseEntity.status(500).body(response);
        }
    }
}
