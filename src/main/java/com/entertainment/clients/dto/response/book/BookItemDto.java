package com.entertainment.clients.dto.response.book;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookItemDto {

    @NotNull(message = "VolumeInfo should be not null")
    private BookDto volumeInfo;
}
