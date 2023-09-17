package com.example.demos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class FileUploadConfiguration {
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(100 * 1024 * 1024); // 设置上传文件的最大大小
        return resolver;
    }
    @Autowired
    @Lazy
    private CommonsMultipartResolver multipartResolver;

    @PostConstruct
    public void init() throws IOException {
        multipartResolver.setUploadTempDir(new FileSystemResource(uploadDir));
    }
}