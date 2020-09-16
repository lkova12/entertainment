package com.entertainment.clients.dto.response.book;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResultDto {

    private Integer totalItems;
    private List<BookItemDto> items;
}
