package com.cs.validation.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import com.cs.validation.service.CurrenciesPairConverter;
import com.cs.validation.service.FixerClient;
import com.cs.validation.service.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValueDateIsNotWeekendOrNonWorkingDay implements ValidationCondition<Trade> {

    private static final String VALIDATION_ERROR_MESSAGE = "Value date for currencies is weekend or non working day.";

    private final FixerClient fixerClient;
    private final CurrenciesPairConverter currenciesPairConverter;

    ValueDateIsNotWeekendOrNonWorkingDay(final FixerClient fixerClient, final CurrenciesPairConverter currenciesPairConverter) {
        this.fixerClient = fixerClient;
        this.currenciesPairConverter = currenciesPairConverter;
    }

    @Override
    public ValidationResult validate(final Trade data) {
        final Pair<String, String> currencies = currenciesPairConverter.convert(data.getCcyPair());
        return fixerClient.hasCurrencyRateForDate(currencies.getFirst(), data.getValueDate()) && fixerClient.hasCurrencyRateForDate(currencies.getSecond(), data.getValueDate())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
