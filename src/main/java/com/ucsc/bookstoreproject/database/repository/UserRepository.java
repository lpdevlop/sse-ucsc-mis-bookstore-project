package com.ucsc.bookstoreproject.database.repository;

import com.ucsc.bookstoreproject.database.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, Long>{


    Optional<UserModel> findByemail(String email);

    UserDetails getUserByEmail(String email);

    Optional<UserModel> findByUuid(UUID uuidString);


    @Query(value = "SELECT u FROM UserModel u where u.email=:email")
    Optional<UserModel> getAnyUserByEmail(@Param("email") String email);


    @Query("""
    SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END
    FROM UserModel b
    WHERE b.isSeedData = true 
  """)
    boolean findDummyData();
}
