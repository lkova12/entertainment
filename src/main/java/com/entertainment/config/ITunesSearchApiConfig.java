package com.entertainment.config;

import com.entertainment.clients.ITunesSearchApi;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ITunesSearchApiConfig {

    @Value("${itunes.api.host}")
    private String iTunesApiHost;

    @Bean
    public ITunesSearchApi iTunesSearchApi() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ITunesSearchApi.class))
                .logLevel(Logger.Level.FULL)
                .target(ITunesSearchApi.class, iTunesApiHost);
    }
}
