package com.ucsc.bookstoreproject.database.model;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BookModel() {
    }

    public BookModel(BookDTO bookDTO) {
        //setter for all fields

        this.title = bookDTO.getTitle();
        this.author = bookDTO.getAuthor();
        this.isbn = bookDTO.getIsbn();
        this.publisher = bookDTO.getPublisher();
        this.publicationDate = bookDTO.getPublicationDate();
        this.price = bookDTO.getPrice();
        this.language = bookDTO.getLanguage();
        this.genre = bookDTO.getGenre();
        this.stockQuantity = bookDTO.getStockQuantity();
        this.description = bookDTO.getDescription();
        this.averageRating = bookDTO.getAverageRating();
        this.pageCount = bookDTO.getPageCount();
        this.format = bookDTO.getFormat();
        this.imageUrl = bookDTO.getImageUrl();
        this.isAvailable = bookDTO.getIsAvailable();
        this.id = bookDTO.getId();
    }
}