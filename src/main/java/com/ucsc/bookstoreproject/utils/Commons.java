package com.ucsc.bookstoreproject.utils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Commons {

    private static final Logger log = LoggerFactory.getLogger(Commons.class);

    public static String readFile(File file) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Error reading file: {}", file.getAbsolutePath(), e);
            throw e;
        }
    }
}