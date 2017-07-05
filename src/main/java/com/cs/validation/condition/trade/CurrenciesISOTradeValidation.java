package com.cs.validation.condition.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import com.cs.validation.service.CurrenciesPairConverter;
import com.cs.validation.service.CurrencyISO;
import javaslang.Tuple2;

public class CurrenciesISOTradeValidation implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE_WRONG_ISO = "Wrong ISO format for currency pair %s. ";

    private final CurrencyISO currencyISO;
    private final CurrenciesPairConverter currenciesPairConverter;

    public CurrenciesISOTradeValidation(final CurrencyISO currencyISO, final CurrenciesPairConverter currenciesPairConverter) {
        this.currencyISO = currencyISO;
        this.currenciesPairConverter = currenciesPairConverter;
    }

    @Override
    public ValidationResult validate(final Trade data) {
        final Tuple2<String, String> currencyPair = currenciesPairConverter.convert(data.getCcyPair());

        return currencyISO.checkValidISO(currencyPair._1) && currencyISO.checkValidISO(currencyPair._2)
                ? ValidationResult.valid()
                : ValidationResult.invalid(String.format(VALIDATION_ERROR_MESSAGE_WRONG_ISO, data.getCcyPair()));

    }
}
