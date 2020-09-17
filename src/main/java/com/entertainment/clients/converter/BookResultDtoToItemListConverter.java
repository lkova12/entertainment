package com.entertainment.clients.converter;

import com.entertainment.clients.ApiUtils;
import com.entertainment.clients.dto.response.book.BookResultDto;
import com.entertainment.domain.DataType;
import com.entertainment.domain.Item;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Convert the response from Google Books API to the list of Item object.
 *  * An example of response from Google Books API:
 *  <pre>
 *{
 *     "kind": "books#volumes",
 *     "totalItems": 2,
 *     "items": [
 *         {
 *             "volumeInfo": {
 *                 "title": "Charpy Impact Test",
 *                 "authors": ["John M. Holt"]
 *                 }
 *          },
 *           {
 *               "volumeInfo": {
 *                   "title": "PRO 31: International RILEM Workshop on Test and Design Methods for Steel
 *                   Fibre Reinforced Concrete - Background and Experiences",
 *                   "authors": ["Bernd Schnütgen",
 *                     "Lucie Vandewalle"]
 *                   }
 *           }]
 *  }
 *  </pre>
 */
@Component
@RequiredArgsConstructor
public class BookResultDtoToItemListConverter implements Converter<BookResultDto, List<Item>> {

    @Override
    public List<Item> convert(BookResultDto dto) {
        return dto.getItems().stream()
                .map(item -> new Item(item.getVolumeInfo().getTitle(),
                        Objects.isNull(item.getVolumeInfo().getAuthors()) ?
                                ApiUtils.EMPTY : String.join(", ", item.getVolumeInfo().getAuthors()),
                        DataType.BOOK))
                .collect(Collectors.toList());
    }
}
