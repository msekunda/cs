package com.cs.validation.condition.spot;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;

public class SpotValueDateValidation implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Difference between trade date and value date is higher than 2 days or value date is not after trade date.";

    @Override
    public ValidationResult validate(final Trade data) {
        return data.getValueDate().isAfter(data.getTradeDate()) && data.getValueDate().isBefore(data.getTradeDate().plusDays(3))
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
