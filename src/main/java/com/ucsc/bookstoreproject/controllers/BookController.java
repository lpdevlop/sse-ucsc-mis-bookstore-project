package com.ucsc.bookstoreproject.controllers;


import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import com.ucsc.bookstoreproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<PayLoadDTO> addBooks(@RequestBody BookDTO bookDTO){

        try {
            PayLoadDTO payLoadDTO=new PayLoadDTO();
            payLoadDTO.put("Book added successfully", bookService.addBooks(bookDTO));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        }catch (Exception e){
            throw e;
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PayLoadDTO> deletebooks(@RequestBody BookDTO bookDTO){
        try {
            PayLoadDTO payLoadDTO=new PayLoadDTO();
            payLoadDTO.put("Book deleted successfully", bookService.deleteBooks(bookDTO));
            return ResponseEntity.status(HttpStatus.OK).body(payLoadDTO);
        }catch (Exception e){
            throw e;
        }
    }


}
