package com.entertainment.service;

import com.entertainment.clients.GoogleBooksApi;
import com.entertainment.clients.converter.Converter;
import com.entertainment.clients.dto.response.book.BookResultDto;
import com.entertainment.config.ApplicationProperties;
import com.entertainment.domain.Item;
import com.entertainment.util.WebUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

/**
 * A service class that calls Google Books API for searching.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookLookupService implements LookupService {

    private final GoogleBooksApi googleBooksApi;
    private final ApplicationProperties applicationProperties;
    private final Converter<BookResultDto, List<Item>> bookResultDtoToItemListConverter;

    /**
     * It calls Google Books API for searching by query value and other predefined parameters
     * also checks if the result is not empty. If the result is empty, the method returns an empty list.
     * If the result is not empty than it converts the API response to the items list.
     *
     * @param query
     * @return a list of result items
     */
    @Override
    public List<Item> findByQuery(String query) {
        val response = googleBooksApi.getBooks(buildQueryParameters(query));
        if (response.getTotalItems() == 0) {
            log.info(String.format("No books found for `%s`", query));
            return Collections.emptyList();
        }
        return bookResultDtoToItemListConverter.convert(response);
    }

    /**
     * It builds all necessary parameters for the proper call to Google Books API
     * and searches by query value, print type and limit result.
     *
     * @param query
     * @return a map of query parameters
     */
    public Map<String, Object> buildQueryParameters(String query) {
        final Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put(WebUtils.Q_QUERY_PARAM, query);
        queryParameters.put(WebUtils.MAX_RESULTS_QUERY_PARAM, applicationProperties.getGoogleBooksApiMaxResult());
        queryParameters.put(WebUtils.PRINT_TYPE_QUERY_PARAM, applicationProperties.getGoogleBooksApiPrintType());
        return queryParameters;
    }
}
