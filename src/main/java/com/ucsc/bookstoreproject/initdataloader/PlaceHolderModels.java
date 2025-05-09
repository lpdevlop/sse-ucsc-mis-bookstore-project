package com.ucsc.bookstoreproject.initdataloader;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import com.ucsc.bookstoreproject.database.dto.UserDTO;
import lombok.Data;

import java.util.List;

@Data

public class PlaceHolderModels {

    private List<BookDTO> books;

    private List<UserDTO> users;

}
