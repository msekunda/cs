package com.cs.validation.condition.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import com.cs.validation.service.CurrenciesPairConverter;
import com.cs.validation.service.FixerClient;
import javaslang.Tuple2;

public class ValueDateIsNotWeekendOrNonWorkingDay implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Value date for currencies is weekend or non working day.";

    private final FixerClient fixerClient;
    private final CurrenciesPairConverter currenciesPairConverter;

    public ValueDateIsNotWeekendOrNonWorkingDay(final FixerClient fixerClient, final CurrenciesPairConverter currenciesPairConverter) {
        this.fixerClient = fixerClient;
        this.currenciesPairConverter = currenciesPairConverter;
    }

    @Override
    public ValidationResult validate(final Trade data) {
        final Tuple2<String, String> currencies = currenciesPairConverter.convert(data.getCcyPair());
        return fixerClient.hasCurrencyRateForDate(currencies._1, data.getValueDate()) && fixerClient.hasCurrencyRateForDate(currencies._2, data.getValueDate())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
