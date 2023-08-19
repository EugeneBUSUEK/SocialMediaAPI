package com.test.socialmedia.api.controller;

import com.test.socialmedia.service.FileService;
import com.test.socialmedia.service.MinioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private final FileService fileService;

    @Operation(summary = "download file")
    @GetMapping("/{object}")
    public void getObject(@PathVariable("object") String object, HttpServletResponse response) throws IOException {
        InputStream inputStream = fileService.getAttachment(object);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        response.addHeader("Content-disposition", "inline;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));

        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @Operation(summary = "upload file")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addAttachment(@RequestParam("file") MultipartFile file) {
        String responseBody = fileService.addAttachment(file);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
