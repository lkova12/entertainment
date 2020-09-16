package com.entertainment.clients.converter;

import com.entertainment.clients.dto.response.album.AlbumResultDto;
import com.entertainment.domain.DataType;
import com.entertainment.domain.Item;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumResultDtoItemListConverter implements Converter<AlbumResultDto, List<Item>> {

    @Override
    public List<Item> convert(AlbumResultDto dto) {
        return dto.getResults().stream()
                .map(item -> new Item(item.getCollectionName(), item.getArtistName(), DataType.ALBUM))
                .collect(Collectors.toList());
    }
}
