package com.ucsc.bookstoreproject.service;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.dto.PaginatedResponseDTO;

public interface BookService {
    Long addBooks(BookDTO bookDTO);

    String deleteBooks(BookDTO bookDTO);

    PaginatedResponseDTO searchBooks(String title, String author, String isbn, String description, int page, int size);

    Object getLatestBooks();

    Object getTopSellingBooks();

    Object getReccomondationsBooks();
}
