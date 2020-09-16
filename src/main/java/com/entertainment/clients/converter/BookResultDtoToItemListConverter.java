package com.entertainment.clients.converter;

import com.entertainment.clients.dto.response.book.BookResultDto;
import com.entertainment.domain.DataType;
import com.entertainment.domain.Item;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookResultDtoToItemListConverter implements Converter<BookResultDto, List<Item>> {

    @Override
    public List<Item> convert(BookResultDto dto) {
        return dto.getItems().stream()
                .map(item -> new Item(item.getVolumeInfo().getTitle(),
                        String.join(", ", item.getVolumeInfo().getAuthors()),
                        DataType.BOOK))
                .collect(Collectors.toList());
    }
}
