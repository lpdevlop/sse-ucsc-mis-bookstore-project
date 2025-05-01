package com.ucsc.bookstoreproject.service.impl;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.model.BookModel;
import com.ucsc.bookstoreproject.database.repository.BookRepository;
import com.ucsc.bookstoreproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Long addBooks(BookDTO bookDTO) {

        Optional<BookModel> bookModel = bookRepository.findById(bookDTO.getId());
        BookModel bookModel1 = new BookModel();

        if (bookModel.isPresent()) {
            bookModel1.setId(bookDTO.getId());
        }
            bookModel1.setId(bookDTO.getId());
            bookModel1.setTitle(bookDTO.getTitle());
            bookModel1.setAuthor(bookDTO.getAuthor());
            bookModel1.setIsbn(bookDTO.getIsbn());
            bookModel1.setPublisher(bookDTO.getPublisher());
            bookModel1.setPublicationDate(bookDTO.getPublicationDate());
            bookModel1.setPrice(bookDTO.getPrice());
            bookModel1.setLanguage(bookDTO.getLanguage());
            bookModel1.setGenre(bookDTO.getGenre());
            bookModel1.setStockQuantity(bookDTO.getStockQuantity());
            bookModel1.setDescription(bookDTO.getDescription());
            bookModel1.setAverageRating(bookDTO.getAverageRating());
            bookModel1.setPageCount(bookDTO.getPageCount());
            bookModel1.setFormat(bookDTO.getFormat());
            bookModel1.setImageUrl(bookDTO.getImageUrl());
            bookModel1.setIsAvailable(bookDTO.getIsAvailable());
            bookRepository.save(bookModel1);
            return bookModel1.getId();
    }

    @Override
    public String deleteBooks(BookDTO bookDTO) {
            bookRepository.deleteById(bookDTO.getId());
            return "Book deleted";
    }

}
