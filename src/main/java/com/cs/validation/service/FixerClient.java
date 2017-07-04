package com.cs.validation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

@Service
public class FixerClient {

    private final RestTemplate restTemplate;

    public FixerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean hasCurrencyRateForDate(final String currency, final LocalDate date) {
        final String url = String.format("http://api.fixer.io/%s?base=%s", date, currency);
        final CurrencyInfo currencyInfo = invoke(createRequestEntity(url), CurrencyInfo.class).getBody();
        return currencyInfo.getDate().isEqual(date);
    }

    private <T> ResponseEntity<T> invoke(final RequestEntity<?> request, Class<T> type) {
        return this.restTemplate.exchange(request, type);
    }

    private RequestEntity<?> createRequestEntity(final String url) {
        try {
            return RequestEntity.get(new URI(url))
                                .accept(MediaType.APPLICATION_JSON).build();
        } catch (URISyntaxException ex) {
            throw new IllegalStateException("Invalid URL " + url, ex);
        }
    }


}
