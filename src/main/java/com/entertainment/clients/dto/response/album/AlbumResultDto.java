package com.entertainment.clients.dto.response.album;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The response object from iTunes Search API call.
 */
@Data
@NoArgsConstructor
public class AlbumResultDto {

    @NotNull(message = "Result count should be not null")
    private Integer resultCount;
    @NotNull(message = "Album list should be not null")
    private List<AlbumDto> results;
}
