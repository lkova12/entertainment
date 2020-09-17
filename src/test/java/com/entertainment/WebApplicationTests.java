package com.entertainment;

import static org.assertj.core.api.Assertions.assertThat;

import com.entertainment.controller.SearchController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

    @Autowired
    private SearchController searchController;

    @Test
    void contextLoads() {
        assertThat(searchController).isNotNull();
    }
}
