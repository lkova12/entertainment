package com.entertainment.service;

import com.entertainment.domain.Item;
import java.util.List;

public interface LookupService {

    List<Item> findByQuery(String query);
}
