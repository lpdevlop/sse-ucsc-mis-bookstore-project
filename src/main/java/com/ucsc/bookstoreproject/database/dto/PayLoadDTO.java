package com.ucsc.bookstoreproject.database.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
public class PayLoadDTO extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    private String status;

    private Object data;


}
