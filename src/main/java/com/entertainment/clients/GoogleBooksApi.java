package com.entertainment.clients;

import com.entertainment.clients.dto.response.book.BookResultDto;
import feign.QueryMap;
import feign.RequestLine;
import java.util.Map;

public interface GoogleBooksApi {

    @RequestLine("GET /books/v1/volumes")
    BookResultDto getBooks(@QueryMap Map<String, Object> queryParameters);
}
