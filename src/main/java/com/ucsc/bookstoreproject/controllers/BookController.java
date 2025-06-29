package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.service.BookService;
import com.ucsc.bookstoreproject.utils.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController extends BaseController{

    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PayLoadDTO> addBooks(@Valid @RequestBody BookDTO bookDTO){

        try {
            PayLoadDTO payLoadDTO=new PayLoadDTO();
            payLoadDTO.put("Book added successfully",bookService.addBooks(bookDTO));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        }catch (Exception e){
            return errorResponse(HttpStatus.BAD_REQUEST, Constant.ERROR_MESSAGE);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PayLoadDTO> deleteBooks(@RequestBody BookDTO bookDTO){
        try {
            PayLoadDTO payLoadDTO=new PayLoadDTO();
            payLoadDTO.put("Book deleted successfully", bookService.deleteBooks(bookDTO));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        }catch (Exception e){
            return errorResponse(HttpStatus.BAD_REQUEST,Constant.ERROR_MESSAGE);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<PayLoadDTO> searchBooks(@RequestBody BookDTO bookDTO) {
        try {
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            payLoadDTO.put("book_searched_successfully", bookService.searchBooks(bookDTO.getTitle(),bookDTO.getAuthor(),bookDTO.getIsbn(),bookDTO.getDescription(),bookDTO.getPage(),bookDTO.getSize()));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,Constant.ERROR_MESSAGE);
        }
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<PayLoadDTO> searchBooksByIsbn(@PathVariable String isbn) {
        try {
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            payLoadDTO.put("book_searched_successfully", bookService.searchBooksByIsbn(isbn));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,Constant.ERROR_MESSAGE);
        }
    }



    @GetMapping("/latest")
    public ResponseEntity<PayLoadDTO> getLatestBooks() {
        try {
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            payLoadDTO.put("Latest books fetched successfully", bookService.getLatestBooks());
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,Constant.ERROR_MESSAGE);
        }
    }
    @GetMapping("/top")
    public ResponseEntity<PayLoadDTO> getTopSellingBooks() {
        try {
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            payLoadDTO.put("top_selling_books_fetched_successfully", bookService.getTopSellingBooks());
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,Constant.ERROR_MESSAGE);
        }
    }

    @GetMapping("/recommendations")
    public ResponseEntity<PayLoadDTO> getRecommendationBooks() {
        try {
            PayLoadDTO payLoadDTO = new PayLoadDTO();
            payLoadDTO.put("recommendations_books_fetched_successfully", bookService.getReccomondationsBooks());
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,Constant.ERROR_MESSAGE);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}/status")
    public ResponseEntity<PayLoadDTO> deactivateBook(@PathVariable long id) {
        PayLoadDTO payLoadDTO = new PayLoadDTO();
        try {
            payLoadDTO.put("Book deactivated successfully", bookService.deactivateBook(id));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        } catch (Exception e) {
            return errorResponse(HttpStatus.BAD_REQUEST,Constant.ERROR_MESSAGE);
        }
    }

}
