package com.ucsc.bookstoreproject.service.impl;

import com.ucsc.bookstoreproject.database.dto.UserDTO;
import com.ucsc.bookstoreproject.database.dto.user.UserPayloadDTO;
import com.ucsc.bookstoreproject.database.dto.user.UserResponseDTO;
import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.database.repository.UserRepository;
import com.ucsc.bookstoreproject.enums.UserRoleEnum;
import com.ucsc.bookstoreproject.exceptions.CustomException;
import com.ucsc.bookstoreproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public long registerUser(UserDTO userDTO) {

        UserModel userModel1 = new UserModel();
        Optional<UserModel> userModel = Optional.ofNullable(userDTO.getId())
                .flatMap(userRepository::findById);
        if(userModel.isPresent()){
            userModel1.setId(userDTO.getId());
            userModel1.setFirstName(userDTO.getFirstName());
            userModel1.setLastName(userDTO.getLastName());
            userModel1.setEmail(userDTO.getEmail());
            userModel1.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userModel1.setAddress(userDTO.getAddress());
            userModel1.setPhoneNumber(userDTO.getPhoneNumber());
            userModel1.setAccountNonLocked(true);
            userModel1.setAccountNonExpired(true);
            userModel1.setCredentialsNonExpired(true);
            userModel1.setEnabled(true);
        }else{
            userModel1.setFirstName(userDTO.getFirstName());
            userModel1.setLastName(userDTO.getLastName());
            userModel1.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userModel1.setEmail(userDTO.getEmail());
            userModel1.setAddress(userDTO.getAddress());
            userModel1.setPhoneNumber(userDTO.getPhoneNumber());
            userModel1.setAccountNonLocked(true);
            userModel1.setAccountNonExpired(true);
            userModel1.setCredentialsNonExpired(true);
            userModel1.setEnabled(true);
        }
        userModel1.setRole(UserRoleEnum.CUSTOMER);
        UserModel userModel2=userRepository.save(userModel1);
        return userModel2.getId();
    }

    @Override
    public long registerAdminUser(UserDTO userDTO) {
        UserModel userModel1 = new UserModel();
        Optional<UserModel> userModel=Optional.ofNullable(userDTO.getId()).flatMap(userRepository::findById);
        if(userModel.isPresent()){
            userModel1.setId(userDTO.getId());
            userModel1.setRole(UserRoleEnum.ADMIN);

            userModel1.setFirstName(UUID.randomUUID().toString());
            userModel1.setLastName(UUID.randomUUID().toString());
            userModel1.setEmail(userDTO.getEmail());
            userModel1.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userModel1.setAddress(UUID.randomUUID().toString());
            userModel1.setPhoneNumber(UUID.randomUUID().toString());
            userModel1.setAccountNonLocked(true);
            userModel1.setAccountNonExpired(true);
            userModel1.setCredentialsNonExpired(true);
            userModel1.setEnabled(true);

        }else{
            Optional<UserModel> anyUserByEmail=userRepository.getAnyUserByEmail(userDTO.getEmail());

            if(anyUserByEmail.isPresent()){
                throw new CustomException("Email already exists", HttpStatus.BAD_REQUEST);
            }

            userModel1.setRole(UserRoleEnum.ADMIN);

            userModel1.setFirstName(UUID.randomUUID().toString());
            userModel1.setLastName(UUID.randomUUID().toString());
            userModel1.setEmail(userDTO.getEmail());
            userModel1.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userModel1.setAddress(UUID.randomUUID().toString());
            userModel1.setPhoneNumber(UUID.randomUUID().toString());
            userModel1.setAccountNonLocked(true);
            userModel1.setAccountNonExpired(true);
            userModel1.setCredentialsNonExpired(true);
            userModel1.setEnabled(true);

        }
        UserModel userModel2=userRepository.save(userModel1);
        return userModel2.getId();
    }

    @Override
    public UserResponseDTO getUserProfile(UserPayloadDTO userDTO) {
            if (userDTO.getId() == null) {
                throw new IllegalArgumentException("User ID must not be null");
            }
            return userRepository.findByUuid(userDTO.getId())
                    .map(userModel -> {
                        UserResponseDTO responseDTO = new UserResponseDTO();
                        responseDTO.setEmail(userModel.getEmail());
                        responseDTO.setLastName(userModel.getLastName());
                        responseDTO.setFirstName(userModel.getFirstName());
                        responseDTO.setRole(userModel.getRole().name());
                        return responseDTO;
                    })
                    .orElseThrow(() -> new CustomException("User not found with ID: ",HttpStatus.BAD_REQUEST));
    }

}
