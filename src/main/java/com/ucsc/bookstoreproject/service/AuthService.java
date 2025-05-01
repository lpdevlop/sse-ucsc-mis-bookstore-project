package com.ucsc.bookstoreproject.service;

public interface AuthService {


    boolean authenticate(String username, String password);
}
