package com.ucsc.bookstoreproject.database.dao;

import com.ucsc.bookstoreproject.database.model.BookModel;
import com.ucsc.bookstoreproject.database.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookDao {

    private final BookRepository bookRepository;


    public void upsert(List<BookModel> roles) {
        bookRepository.saveAll(roles);
    }
}
