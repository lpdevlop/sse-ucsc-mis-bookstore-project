package com.ucsc.bookstoreproject.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;

@RestController
public class ImageController {


    @GetMapping(value = "/images/{imageName:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        String safeImageName = Paths.get(imageName).getFileName().toString();
        ClassPathResource imgFile = new ClassPathResource("static/images/" + safeImageName);
        if (imgFile.exists()) {
            String contentType = imageName.endsWith(".png") ? MediaType.IMAGE_PNG_VALUE : MediaType.IMAGE_JPEG_VALUE;
            response.setContentType(contentType);
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-Frame-Options", "DENY");
            response.setHeader("Cache-Control", "no-store, must-revalidate");
            StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        } else {
            String imageNames="na";
            String contentType = imageNames.endsWith(".jpeg") ? MediaType.IMAGE_PNG_VALUE : MediaType.IMAGE_JPEG_VALUE;
            ClassPathResource naFile = new ClassPathResource("static/images/" + imageNames.concat(".jpeg"));
            response.setContentType(contentType);
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-Frame-Options", "DENY");
            response.setHeader("Cache-Control", "no-store, must-revalidate");
            StreamUtils.copy(naFile.getInputStream(), response.getOutputStream());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test successful");
    }
}
