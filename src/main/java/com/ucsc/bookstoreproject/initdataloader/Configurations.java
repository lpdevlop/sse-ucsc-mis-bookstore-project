package com.ucsc.bookstoreproject.initdataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucsc.bookstoreproject.utils.Commons;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@Slf4j
public class Configurations {


    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ObjectMapper objectMapper;

    @Getter
    private PlaceHolderModels placeholderModel;

    @PostConstruct
    public void init() throws IOException {
        this.initialize();
    }

    public void initialize() throws IOException {
        try {
            Resource resource = resourceLoader.getResource("classpath:data.json");
            String content = Commons.readFile(resource.getFile());
            this.placeholderModel = objectMapper.readValue(content, PlaceHolderModels.class);
            log.info("Placeholder data loaded successfully.");
        } catch (IOException e) {
            log.error("Error reading or parsing the data file: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while loading data: {}", e.getMessage(), e);
            throw new IOException("Failed to load configuration", e);
        }
    }
}