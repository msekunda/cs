package com.cs.validation.condition.option;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import com.cs.validation.service.CurrencyISO;

public class CurrenciesISOOptionValidation implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Option currency is not in valid ISO format.";
    private CurrencyISO currencyISO;

    public CurrenciesISOOptionValidation(final CurrencyISO currencyISO) {
        this.currencyISO = currencyISO;
    }

    @Override
    public ValidationResult validate(final Trade data) {
        return currencyISO.checkValidISO(data.getPremiumCcy()) && currencyISO.checkValidISO(data.getPayCcy())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);

    }
}
