package com.ucsc.bookstoreproject.service;

import com.ucsc.bookstoreproject.database.dto.BookDTO;

import java.util.List;

public interface BookService {
    Long addBooks(BookDTO bookDTO);

    String deleteBooks(BookDTO bookDTO);

    List<BookDTO> searchBooks(BookDTO bookDTO);
}
