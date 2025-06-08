package com.ucsc.bookstoreproject.database.repository;

import com.ucsc.bookstoreproject.database.model.BookModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long>, JpaSpecificationExecutor<BookModel> {
    BookModel findByisbn(String isbn);

    @Modifying
    @Transactional
    @Query("""
    UPDATE BookModel b
      SET b.active = CASE WHEN b.active = TRUE THEN FALSE ELSE TRUE END
    WHERE b.id = :id
  """)    Integer updateBookStatus(@Param("id") Long id);
}
