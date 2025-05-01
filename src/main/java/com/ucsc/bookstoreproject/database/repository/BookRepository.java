package com.ucsc.bookstoreproject.database.repository;

import com.ucsc.bookstoreproject.database.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long>{
}
