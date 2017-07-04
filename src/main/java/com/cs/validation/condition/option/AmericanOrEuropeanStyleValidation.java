package com.cs.validation.condition.option;

import com.cs.domain.Option;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class AmericanOrEuropeanStyleValidation implements ValidationCondition<Option> {

    private static final String VALIDATION_ERROR_MESSAGE = "Style is not supported.";

    @Override
    public ValidationResult validate(final Option data) {
        return "EUROPEAN".equals(data.getStyle()) || "AMERICAN".equals(data.getStyle())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
