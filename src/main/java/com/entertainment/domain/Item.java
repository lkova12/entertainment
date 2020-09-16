package com.entertainment.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item implements Serializable {

    private String title;
    private String authors;
    private String type;
}
