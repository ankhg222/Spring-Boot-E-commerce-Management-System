package vn.iot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api")
public class FileUploadController_23110236 {
    
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        
        try {
            // Kiểm tra file có tồn tại không
            if (file.isEmpty()) {
                response.put("error", "File không được để trống");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Kiểm tra định dạng file
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                response.put("error", "Chỉ được upload file hình ảnh");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Tạo tên file unique
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            
            // Tạo thư mục nếu chưa tồn tại
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            // Lưu file
            Path filePath = Paths.get(uploadPath, uniqueFilename);
            Files.copy(file.getInputStream(), filePath);
            
            // Trả về URL của file đã upload
            String fileUrl = "/images/uploads/" + uniqueFilename;
            response.put("success", "Upload thành công");
            response.put("url", fileUrl);
            response.put("filename", uniqueFilename);
            
            return ResponseEntity.ok(response);
            
        } catch (IOException e) {
            response.put("error", "Lỗi khi upload file: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    @DeleteMapping("/delete/{filename}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteFile(@PathVariable String filename) {
        Map<String, String> response = new HashMap<>();
        
        try {
            Path filePath = Paths.get(uploadPath, filename);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                response.put("success", "Xóa file thành công");
                return ResponseEntity.ok(response);
            } else {
                response.put("error", "File không tồn tại");
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            response.put("error", "Lỗi khi xóa file: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
