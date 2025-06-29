package com.ucsc.bookstoreproject.database.dto;

import com.ucsc.bookstoreproject.database.model.BookModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BookDTO {

    private Integer orderQuantity;

    private Long id;

    private Boolean active;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String publicationDate;
    private Double price;
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

    public BookDTO(BookModel bookModel){
        this.id = bookModel.getId();
        this.title = bookModel.getTitle();
        this.author = bookModel.getAuthor();
        this.isbn = bookModel.getIsbn();
        this.publisher = bookModel.getPublisher();
        this.publicationDate = bookModel.getPublicationDate();
        this.price = bookModel.getPrice();
        this.language = bookModel.getLanguage();
        this.genre = bookModel.getGenre();
        this.stockQuantity = bookModel.getStockQuantity();
        this.description = bookModel.getDescription();
        this.averageRating = bookModel.getAverageRating();
        this.pageCount = bookModel.getPageCount();
        this.format = bookModel.getFormat();
        this.imageUrl = bookModel.getImageUrl();
        this.isAvailable = bookModel.getIsAvailable();
    }

}
