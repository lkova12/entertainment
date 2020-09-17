package com.entertainment.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * It represents the search result and is used to show the result on UI.
 */
@Data
@AllArgsConstructor
public class Item implements Serializable {

    private String title;
    private String authors;
    private String type;
}
