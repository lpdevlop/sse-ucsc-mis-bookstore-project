package com.ucsc.bookstoreproject.database.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class PayLoadDTO extends HashMap<String, Object> {

    private String message;

    private String status;

    private Object data;


}
