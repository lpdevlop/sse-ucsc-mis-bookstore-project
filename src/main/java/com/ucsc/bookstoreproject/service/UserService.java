package com.ucsc.bookstoreproject.service;

import com.ucsc.bookstoreproject.database.dto.UserDTO;
import com.ucsc.bookstoreproject.database.dto.user.UserPayloadDTO;
import com.ucsc.bookstoreproject.database.dto.user.UserResponseDTO;

public interface UserService {
    long registerUser(UserDTO userDTO);

    long registerAdminUser(UserDTO userDTO);

    UserResponseDTO getUserProfile(UserPayloadDTO userDTO);
}
