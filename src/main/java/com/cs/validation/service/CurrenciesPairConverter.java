package com.cs.validation.service;

import org.springframework.stereotype.Service;

@Service
public class CurrenciesPairConverter {

    public Pair<String, String> convert(final String currencyPair) {
        if (currencyPair.length() != 6) {
            throw new IllegalArgumentException("Currency pair can only have 6 letters");
        }
        final String first = currencyPair.substring(0, 3);
        final String second = currencyPair.substring(3);
        return new Pair<>(first, second);
    }

}
