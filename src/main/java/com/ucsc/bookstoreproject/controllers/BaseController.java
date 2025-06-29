package com.ucsc.bookstoreproject.controllers;

import com.ucsc.bookstoreproject.database.dto.PayLoadDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public class BaseController {


    public ResponseEntity<PayLoadDTO> errorResponse(HttpStatusCode httpStatus, String body){
        PayLoadDTO payLoadDTO=new PayLoadDTO();
        payLoadDTO.put("data",body);
        return  ResponseEntity.status(httpStatus).body(payLoadDTO);
    }

}
