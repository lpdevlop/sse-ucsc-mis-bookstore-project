package com.ucsc.bookstoreproject.database.filters;

import com.ucsc.bookstoreproject.database.model.BookModel;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<BookModel> hasTitle(String title){
        return (root,query,criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title + "%");
    }

    public static Specification<BookModel> hasAuthor(String author){
        return (root,query,criteriaBuilder) -> criteriaBuilder.like(root.get("author"),author);
    }

    public static Specification<BookModel> hasIsbn(String isbn){
        return (root,query, criteriaBuilder)-> criteriaBuilder.equal(root.get("isbn"),isbn);
    }

    public static Specification<BookModel> hasDescription(String description){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("description"), description));
    }
}
