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

@Slf4j
@Service
@RequiredArgsConstructor
public class ITunesLookupService implements LookupService {

    private final ITunesSearchApi iTunesSearchApi;
    private final ApplicationProperties applicationProperties;
    private final Converter<AlbumResultDto, List<Item>> albumResultDtoItemListConverter;

    @Override
    public List<Item> findByQuery(String query) {
        val response = iTunesSearchApi.getAlbums(buildQueryParameters(query));
        if (response.getResultCount() == 0) {
            log.info(String.format("No albums found for `%s`", query));
            return Collections.emptyList();
        }
        return albumResultDtoItemListConverter.convert(response);
    }

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
