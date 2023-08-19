package com.test.socialmedia.service;

import com.test.socialmedia.support.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileService {

    private final MinioService minioService;

    public InputStream getAttachment(String fileName) {
        return minioService.getFile(fileName);
    }

    public String addAttachment(MultipartFile file) {
        if (!FileUtil.isImage(file)) {
            throw new RuntimeException("File extension must be .jpg or .png"); //TODO exception;
        }
        return minioService.upload(file);
    }
}
