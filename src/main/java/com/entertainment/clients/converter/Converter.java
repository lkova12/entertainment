package com.entertainment.clients.converter;

public interface Converter<S, D> {

    D convert(S src);
}
