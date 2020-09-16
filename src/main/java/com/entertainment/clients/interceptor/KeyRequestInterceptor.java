package com.entertainment.clients.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class KeyRequestInterceptor implements RequestInterceptor {

    private static final String PARAM_KEY = "key";

    private final String key;

    public KeyRequestInterceptor(final String key) {
        this.key = key;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query(PARAM_KEY, key);
    }
}
