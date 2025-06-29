package com.ucsc.bookstoreproject.service.impl;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.dto.PaginatedResponseDTO;
import com.ucsc.bookstoreproject.database.filters.BookSpecification;
import com.ucsc.bookstoreproject.database.model.BookModel;
import com.ucsc.bookstoreproject.database.repository.BookRepository;
import com.ucsc.bookstoreproject.exceptions.CustomException;
import com.ucsc.bookstoreproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Long addBooks(BookDTO bookDTO) {

        BookModel bookModel1 = new BookModel();

        if(Objects.nonNull(bookDTO.getId())){
            Optional<BookModel> bookModel = bookRepository.findById(bookDTO.getId());
            if (bookModel.isPresent()) {
                bookModel1.setId(bookDTO.getId());
            }else {
                throw new CustomException("Book cannot be null", HttpStatus.NOT_FOUND);
            }
        }
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
         return bookRepository.save(bookModel1).getId();
    }

    @Override
    public String deleteBooks(BookDTO bookDTO) {
            bookRepository.deleteById(bookDTO.getId());
            return "Book deleted";
    }

    @Override
    public  PaginatedResponseDTO searchBooks(String title, String author, String isbn, String description, int page, int size) {
        Specification<BookModel> spec = (root, query, cb) -> cb.conjunction();

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
        Page<BookModel> resultPage = bookRepository.findAll(spec,pageable);

        List<BookDTO> dtos = resultPage.getContent()
                .stream()
                .map(BookDTO::new)
                .toList();

        return new PaginatedResponseDTO<>(
                dtos,
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getTotalElements(),
                resultPage.getTotalPages()
        );
    }

    @Override
    public Object getLatestBooks() {
        return bookRepository.findAll().stream()
                .filter(BookModel::getActive)
                .sorted(Comparator.comparing(BookModel::getTitle))
                .limit(10)
                .toList();

    }

    @Override
    public Object getTopSellingBooks() {
        return bookRepository.findAll().stream().filter(k->k.getActive().equals(true)).limit(4).toList();
    }

    @Override
    public Object getReccomondationsBooks() {
        return bookRepository.findAll().stream().filter(k->k.getActive().equals(true)).limit(10).toList();
    }

    @Override
    public BookDTO searchBooksByIsbn(String isbn) {
        return Stream.of(bookRepository.findByisbn(isbn)).map(BookDTO::new).findAny().orElse(null);
    }

    @Override
    public Boolean deactivateBook(Long id) {
        bookRepository.updateBookStatus(id);
        return true;
    }

}
