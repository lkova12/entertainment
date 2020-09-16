package com.entertainment.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
public class ApplicationProperties {

    @Value("${itunes.api.max.result:5}")
    private String iTunesApiMaxResult;

    @Value("${itunes.api.media:music}")
    private String iTunesApiMedia;

    @Value("${itunes.api.entity:album}")
    private String iTunesApiEntity;

    @Value("${itunes.api.attribute:albumTerm}")
    private String iTunesApiAttribute;

    @Value("${google.books.api.max.result:5}")
    public String googleBooksApiMaxResult;

    @Value("${google.books.api.printType:books}")
    public String googleBooksApiPrintType;
}
