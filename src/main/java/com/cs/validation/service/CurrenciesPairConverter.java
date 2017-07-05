package com.cs.validation.service;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.springframework.stereotype.Service;

@Service
public class CurrenciesPairConverter {

    public Tuple2<String, String> convert(final String currencyPair) {
        if (currencyPair.length() != 6) {
            throw new IllegalArgumentException("Currency pair can only have 6 letters");
        }
        final String first = currencyPair.substring(0, 3);
        final String second = currencyPair.substring(3);
        return Tuple.of(first, second);
    }

}
