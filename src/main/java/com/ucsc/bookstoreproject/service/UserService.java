package com.ucsc.bookstoreproject.service;

import com.ucsc.bookstoreproject.database.dto.UserDTO;

public interface UserService {
    long registerUser(UserDTO userDTO);

    long registerAdminUser(UserDTO userDTO);
}
