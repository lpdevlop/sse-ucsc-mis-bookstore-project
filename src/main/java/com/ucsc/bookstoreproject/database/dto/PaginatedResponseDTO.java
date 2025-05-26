package com.ucsc.bookstoreproject.database.dto;

import com.ucsc.bookstoreproject.database.model.BookModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedResponseDTO<T>{
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PaginatedResponseDTO(BookModel bookModel) {

    }
}
