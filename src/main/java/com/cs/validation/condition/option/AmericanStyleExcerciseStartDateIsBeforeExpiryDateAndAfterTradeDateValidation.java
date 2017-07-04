package com.cs.validation.condition.option;

import com.cs.domain.Option;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidation implements ValidationCondition<Option> {

    private static final String VALIDATION_ERROR_MESSAGE = "Excercise start date is not valid.";

    @Override
    public ValidationResult validate(final Option data) {
        return "AMERICAN".equals(data.getStyle())
                && data.getExcerciseStartDate() != null
                && (data.getExcerciseStartDate().isAfter(data.getTradeDate()) && data.getExcerciseStartDate().isBefore(data.getExpiryDate()))
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
