package com.ucsc.bookstoreproject.database.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookDTO {
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String publisher;

    private LocalDate publicationDate;

    private BigDecimal price;

    private String language;

    private String genre;

    private Integer stockQuantity;

    private String description;

    private Double averageRating;

    private Integer pageCount;

    private String format;

    private String imageUrl;

    private Boolean isAvailable;


}
