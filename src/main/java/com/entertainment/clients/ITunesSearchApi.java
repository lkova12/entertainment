package com.entertainment.clients;

import com.entertainment.clients.dto.response.album.AlbumResultDto;
import feign.QueryMap;
import feign.RequestLine;
import java.util.Map;

public interface ITunesSearchApi {

    @RequestLine("GET /search")
    AlbumResultDto getAlbums(@QueryMap Map<String, Object> queryParameters);
}
