package com.ucsc.bookstoreproject.service;

import com.ucsc.bookstoreproject.database.dto.BookDTO;

public interface BookService {
    Long addBooks(BookDTO bookDTO);

    String deleteBooks(BookDTO bookDTO);
}
