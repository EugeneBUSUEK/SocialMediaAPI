package com.test.socialmedia.api.controller;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Tag(name = "Files", description = "Files resources")
public class FileController {

//    private final MinioService minioService;
//
//    @Operation(summary = "download file")
//    @GetMapping("/{object}")
//    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws MinioException, IOException {
//        InputStream inputStream = minioService.get(Path.of(object));
//        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
//
//        response.addHeader("Content-disposition", "attachment;filename=" + object);
//        response.setContentType(URLConnection.guessContentTypeFromName(object));
//
//        IOUtils.copy(inputStream, response.getOutputStream());
//        response.flushBuffer();
//    }
//
//    @Operation(summary = "upload file")
//    @PostMapping
//    public void addAttachment(@RequestParam("file") MultipartFile file) {
//        Path path = Path.of(file.getOriginalFilename());
//        try {
//            minioService.upload(path, file.getInputStream(), file.getContentType());
//        } catch (MinioException e) {
//            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
//        } catch (IOException e) {
//            throw new IllegalStateException("The file cannot be read", e);
//        }
//    }
}
