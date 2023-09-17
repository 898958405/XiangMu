package com.example.demos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin // 允许跨域
public class UploadController {
//    @Autowired
//    private HttpServletRequest request;
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            // 获取上传文件所在位置的绝对路径
            String absolutePath = new File(filePath).getAbsolutePath();
            System.out.println("上传成功：" + absolutePath);
            return absolutePath;
        } catch (IOException e) {
            System.out.println("上传失败：" + e.getMessage());
            return e.getMessage();
        }
    }
}