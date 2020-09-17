package com.entertainment.service;

import com.entertainment.clients.ITunesSearchApi;
import com.entertainment.clients.converter.Converter;
import com.entertainment.clients.dto.response.album.AlbumResultDto;
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
 * A service class that calls iTunes Search API for searching media content.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ITunesLookupService implements LookupService {

    private final ITunesSearchApi iTunesSearchApi;
    private final ApplicationProperties applicationProperties;
    private final Converter<AlbumResultDto, List<Item>> albumResultDtoItemListConverter;

    /**
     * It calls iTunes Search API for searching media content by query value and other predefined parameters
     * also checks if the result is not empty. If the result is empty, the method returns an empty list.
     * If the result is not empty than it converts the API response to the items list.
     *
     * @param query
     * @return a list of result items
     */
    @Override
    public List<Item> findByQuery(String query) {
        val response = iTunesSearchApi.getAlbums(buildQueryParameters(query));
        if (response.getResultCount() == 0) {
            log.info(String.format("No albums found for `%s`", query));
            return Collections.emptyList();
        }
        return albumResultDtoItemListConverter.convert(response);
    }

    /**
     * It builds all necessary parameters for the proper call to iTunes Search API and searches by query value and
     * specified parameters: media, entity, attribute and limit result.
     *
     * @param query
     * @return a map of query parameters
     */
    public Map<String, Object> buildQueryParameters(String query) {
        final Map<String, Object> queryParameters = new HashMap<>();
        queryParameters.put(WebUtils.TERM_QUERY_PARAM, query);
        queryParameters.put(WebUtils.MEDIA_QUERY_PARAM, applicationProperties.getITunesApiMedia());
        queryParameters.put(WebUtils.ENTITY_QUERY_PARAM, applicationProperties.getITunesApiEntity());
        queryParameters.put(WebUtils.ATTRIBUTE_QUERY_PARAM, applicationProperties.getITunesApiAttribute());
        queryParameters.put(WebUtils.LIMIT_QUERY_PARAM, applicationProperties.getITunesApiMaxResult());
        return queryParameters;
    }
}
