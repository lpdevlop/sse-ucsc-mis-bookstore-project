package com.ucsc.bookstoreproject.database.dao;


import com.ucsc.bookstoreproject.database.model.UserModel;
import com.ucsc.bookstoreproject.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;


    public void upsert(List<UserModel> userModel){
        userRepository.saveAll(userModel);
    }

    public boolean findDummyData() {
        return userRepository.findDummyData();
    }
}
