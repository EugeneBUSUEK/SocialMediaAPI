package com.test.socialmedia.support.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FileUtil {

    public static String generateFileName(MultipartFile file) {
        String extension = getExtension(file);
        return UUID.randomUUID() + "." + extension;
    }

    public static String getExtension(MultipartFile file) {
        return file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    public static boolean isImage(MultipartFile file) {
        String extension = getExtension(file);
        return extension.equals("jpg") || extension.equals("png");
    }
}
