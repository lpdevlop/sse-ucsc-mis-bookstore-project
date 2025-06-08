package com.ucsc.bookstoreproject.initdataloader;

import com.ucsc.bookstoreproject.database.dao.BookDao;
import com.ucsc.bookstoreproject.database.dao.UserDao;
import com.ucsc.bookstoreproject.database.model.BookModel;
import com.ucsc.bookstoreproject.database.model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements ApplicationRunner {

    private final Configurations configurations;

    private final BookDao bookDao;

    private final UserDao userDao;

    @Value("${data.load.enabled:true}")
    private Boolean dataInsertEnabled;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(Boolean.FALSE.equals(dataInsertEnabled)) {
            log.info("......Data Insert Disabled........");
        } else {
            try {
/*
                List<BookModel> roles = configurations.getPlaceholderModel().getBooks().stream().map(BookModel::new).toList();
                bookDao.upsert(roles);
*/

              /*  List<UserModel> users = configurations.getPlaceholderModel().getUsers().stream().map(UserModel::new).toList();
                userDao.upsert(users);*/

                log.info("Initial Data Insert Success");
            } catch (Exception e) {
                log.error("Error during data insert: {}", e.getMessage());
            }
        }
    }
}