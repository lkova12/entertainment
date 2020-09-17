package com.entertainment.clients.dto.response.book;

import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The response object from Google Books API call.
 */
@Data
@NoArgsConstructor
public class BookResultDto {

    @NotNull(message = "Total items count should be not null")
    private Integer totalItems;
    @NotNull(message = "Items list should be not null")
    private List<BookItemDto> items;
}
