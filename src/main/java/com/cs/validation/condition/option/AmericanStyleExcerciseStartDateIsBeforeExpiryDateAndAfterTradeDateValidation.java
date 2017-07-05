package com.cs.validation.condition.option;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;

public class AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidation implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Excercise start date is not valid.";

    @Override
    public ValidationResult validate(final Trade data) {
        return "AMERICAN".equals(data.getStyle())
                && data.getExcerciseStartDate() != null
                && (data.getExcerciseStartDate().isAfter(data.getTradeDate()) && data.getExcerciseStartDate().isBefore(data.getExpiryDate()))
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
