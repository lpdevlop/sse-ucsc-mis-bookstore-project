package com.ucsc.bookstoreproject.service.impl;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.dto.PaginatedResponseDTO;
import com.ucsc.bookstoreproject.database.filters.BookSpecification;
import com.ucsc.bookstoreproject.database.model.BookModel;
import com.ucsc.bookstoreproject.database.repository.BookRepository;
import com.ucsc.bookstoreproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public PaginatedResponseDTO searchBooks(String title, String author, String isbn, String description, int page, int size) {
        Specification<BookModel> spec = Specification.where(null);

        if (title != null && !title.isBlank()) {
            spec = spec.and(BookSpecification.hasTitle(title));
        }

        if (author != null && !author.isBlank()) {
            spec = spec.and(BookSpecification.hasAuthor(author));
        }

        if (isbn != null && !isbn.isBlank()) {
            spec = spec.and(BookSpecification.hasIsbn(isbn));
        }

        if (description != null && !description.isBlank()) {
            spec = spec.and(BookSpecification.hasDescription(description));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return bookRepository.findAll(spec, pageable).stream().map(PaginatedResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public Object getLatestBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Object getTopSellingBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Object getReccomondationsBooks() {
        return bookRepository.findAll();
    }

}
