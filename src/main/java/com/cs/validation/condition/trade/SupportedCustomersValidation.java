package com.cs.validation.condition.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;

import java.util.Arrays;
import java.util.List;

public class SupportedCustomersValidation implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Customer is not supported.";
    private static final List SUPPORTED_CUSTOMERS = Arrays.asList(
            "PLUTO1",
            "PLUTO2"
    );

    @Override
    public ValidationResult validate(final Trade data) {
        return SUPPORTED_CUSTOMERS.contains(data.getCustomer())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
