package com.ucsc.bookstoreproject.initdataloader;

import com.ucsc.bookstoreproject.database.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlaceHolderModels {

    private List<BookDTO> bookDTO;

}
