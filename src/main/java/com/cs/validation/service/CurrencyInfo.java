package com.cs.validation.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
class CurrencyInfo {
    private final String base;
    private final LocalDate date;
}