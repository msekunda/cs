package com.cs.validation.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyISO {

    private static final List CURRENCIES_ISO = Arrays.asList(
            "USD",
            "EUR"
    );

    public boolean checkValidISO(final String currency) {
        return CURRENCIES_ISO.contains(currency);
    }

}
