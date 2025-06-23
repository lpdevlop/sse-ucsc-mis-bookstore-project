package com.ucsc.bookstoreproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookstoreProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreProjectApplication.class, args);
    }


}
