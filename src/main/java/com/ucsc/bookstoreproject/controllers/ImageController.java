package com.ucsc.bookstoreproject.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ImageController {


    @GetMapping(value = "/images/{imageName:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public void getImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/images/" + imageName);
        if (imgFile.exists()) {
            String contentType = imageName.endsWith(".png") ? MediaType.IMAGE_PNG_VALUE : MediaType.IMAGE_JPEG_VALUE;
            response.setContentType(contentType);
            StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
