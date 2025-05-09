package com.ucsc.bookstoreproject.database.repository;

import com.ucsc.bookstoreproject.database.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long>{


    Optional<UserModel> findByemail(String email);

    UserDetails getUserByEmail(String email);
}
