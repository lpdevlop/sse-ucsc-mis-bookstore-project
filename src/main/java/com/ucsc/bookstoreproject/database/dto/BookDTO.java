package com.ucsc.bookstoreproject.database.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BookDTO {


    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publicationDate;
    private Long price;
    private String language;
    private String genre;
    private Integer stockQuantity;
    private String description;
    private Double averageRating;
    private Integer pageCount;
    private String format;
    private String imageUrl;
    private Boolean isAvailable;

    private Integer page;

    private Integer size;


}
