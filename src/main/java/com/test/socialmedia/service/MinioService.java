package com.test.socialmedia.service;

import com.test.socialmedia.support.util.FileUtil;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinioService {

    @Value("${minio.bucket}")
    private String bucket;

    private final MinioClient minioClient;

    public String upload(MultipartFile file) {
        try {
            createBucket();
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed" + e.getMessage()); //TODO Exception
        }

        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new RuntimeException("File is empty"); //TODO Exception
        }

        String fileName = FileUtil.generateFileName(file);

        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed" + e.getMessage()); //TODO Exception
        }

        saveFile(inputStream, fileName);

        return fileName;
    }

    @SneakyThrows
    private void createBucket() {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(this.bucket)
                .build());

        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(this.bucket)
                    .build());
        }
    }

    @SneakyThrows
    private void saveFile(InputStream inputStream, String fileName) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(this.bucket)
                .object(fileName)
                .build());
    }

    @SneakyThrows
    public InputStream getFile(String object) {
        InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(this.bucket)
                .object(object)
                .build());

        return inputStream;
    }
}
