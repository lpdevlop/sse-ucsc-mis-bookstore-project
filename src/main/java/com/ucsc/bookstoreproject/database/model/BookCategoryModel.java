package com.ucsc.bookstoreproject.database.model;


import jakarta.persistence.*;

@Entity
@Table(name = "book_categories")
public class BookCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
