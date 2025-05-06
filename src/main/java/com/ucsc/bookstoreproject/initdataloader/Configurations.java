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
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", e.getMessage());
            throw e;
        } catch (IOException e) {
            log.error("Error reading file: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error while trying to load data from file: {}", e.getMessage());
            throw e;
        }
    }
}