package com.entertainment.config;

import com.entertainment.clients.ITunesSearchApi;
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
 * A configuration bean for working with iTunes Search API.
 */
@Configuration
public class ITunesSearchApiConfig {

    @Value("${itunes.api.host}")
    private String iTunesApiHost;

    /**
     * Set up all necessary properties for Feign to be able to connect to iTunes Search API.
     * Register ITunesSearchApi been in a system.
     *
     * @return ITunesSearchApi instance
     */
    @Bean
    public ITunesSearchApi iTunesSearchApi() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ITunesSearchApi.class))
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(60000,60000))
                .target(ITunesSearchApi.class, iTunesApiHost);
    }
}
