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

@Slf4j
@Service
@RequiredArgsConstructor
public class BookLookupService implements LookupService {

    private final GoogleBooksApi googleBooksApi;
    private final ApplicationProperties applicationProperties;
    private final Converter<BookResultDto, List<Item>> bookResultDtoToItemListConverter;

    @Override
    public List<Item> findByQuery(String query) {
        val response = googleBooksApi.getBooks(buildQueryParameters(query));
        if (response.getTotalItems() == 0) {
            log.info(String.format("No books found for `%s`", query));
            return Collections.emptyList();
        }
        return bookResultDtoToItemListConverter.convert(response);
    }

    public Map<String, Object> buildQueryParameters(String query) {
        final Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put(WebUtils.Q_QUERY_PARAM, query);
        queryParameters.put(WebUtils.MAX_RESULTS_QUERY_PARAM, applicationProperties.getGoogleBooksApiMaxResult());
        queryParameters.put(WebUtils.PRINT_TYPE_QUERY_PARAM, applicationProperties.getGoogleBooksApiPrintType());
        return queryParameters;
    }
}
