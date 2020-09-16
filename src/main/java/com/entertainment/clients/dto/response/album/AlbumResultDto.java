package com.entertainment.clients.dto.response.album;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumResultDto {

    private Integer resultCount;
    private List<AlbumDto> results;
}
