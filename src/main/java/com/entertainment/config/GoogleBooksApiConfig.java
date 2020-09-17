package com.entertainment.config;

import com.entertainment.clients.GoogleBooksApi;
import com.entertainment.clients.interceptor.KeyRequestInterceptor;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A configuration bean for working with Google Books API.
 */
@Configuration
public class GoogleBooksApiConfig {

    @Value("${google.books.api.host}")
    private String googleBooksApiHost;

    @Value("${google.books.api.key}")
    private String key;

    /**
     * Set up all necessary properties for Feign to be able to connect to Google Books API.
     * Register GoogleBooksApi been in a system.
     *
     * @return GoogleBooksApi instance
     */
    @Bean
    public GoogleBooksApi googleBooksApi() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(GoogleBooksApi.class))
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(60000, 60000))
                .requestInterceptor(new KeyRequestInterceptor(key))
                .target(GoogleBooksApi.class, googleBooksApiHost);
    }
}
