package com.ucsc.bookstoreproject.database.model;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;

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


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public BookModel() {
    }

    public BookModel(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.author = bookDTO.getAuthor();
        this.isbn = bookDTO.getIsbn();
        this.publisher = bookDTO.getPublisher();
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