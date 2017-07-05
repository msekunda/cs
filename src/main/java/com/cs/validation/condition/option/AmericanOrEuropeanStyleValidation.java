package com.cs.validation.condition.option;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;

public class AmericanOrEuropeanStyleValidation implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Style is not supported.";

    @Override
    public ValidationResult validate(final Trade data) {
        return "EUROPEAN".equals(data.getStyle()) || "AMERICAN".equals(data.getStyle())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
