package com.entertainment.clients.dto.response.book;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {

    private String title;
    private List<String> authors;
}
