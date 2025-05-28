package com.ucsc.bookstoreproject.service.impl;

import com.ucsc.bookstoreproject.database.dto.UserDTO;
import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.database.repository.UserRepository;
import com.ucsc.bookstoreproject.enums.UserRoleEnum;
import com.ucsc.bookstoreproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
        userModel1.setRole(UserRoleEnum.ROLE_CUSTOMER);
        UserModel userModel2=userRepository.save(userModel1);
        return userModel2.getId();
    }

    @Override
    public long registerAdminUser(UserDTO userDTO) {
        UserModel userModel1 = new UserModel();
        Optional<UserModel> userModel =userRepository.findById(userDTO.getId());
        if(userModel.isPresent()){
            userModel1.setId(userDTO.getId());
            userModel1.setRole(UserRoleEnum.ROLE_ADMIN);
            userModel1.setFirstName(userDTO.getFirstName());
            userModel1.setLastName(userDTO.getLastName());
            userModel1.setPassword(userDTO.getPassword());
        }else{
            userModel1.setFirstName(userDTO.getFirstName());
            userModel1.setRole(UserRoleEnum.ROLE_ADMIN);
            userModel1.setLastName(userDTO.getLastName());
            userModel1.setPassword(userDTO.getPassword());
        }
        UserModel userModel2=userRepository.save(userModel1);
        return userModel2.getId();
    }

}
