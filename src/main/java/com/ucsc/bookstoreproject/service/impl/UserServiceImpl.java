package com.ucsc.bookstoreproject.service.impl;

import com.ucsc.bookstoreproject.database.dto.UserDTO;
import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.database.repository.UserRepository;
import com.ucsc.bookstoreproject.enums.UserRoleEnum;
import com.ucsc.bookstoreproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public long registerUser(UserDTO userDTO) {

        UserModel userModel1 = new UserModel();
        Optional<UserModel> userModel =userRepository.findById(userDTO.getId());
        if(userModel.isPresent()){
            userModel1.setId(userDTO.getId());
            userModel1.setFirstName(userDTO.getFirstName());
            userModel1.setLastName(userDTO.getLastName());
            userModel1.setPassword(userDTO.getPassword());
        }else{
            userModel1.setFirstName(userDTO.getFirstName());
            userModel1.setLastName(userDTO.getLastName());
            userModel1.setPassword(userDTO.getPassword());
        }
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
