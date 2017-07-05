package com.cs.validation.condition.forward;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;

/*
* The same case TradeDateBeforeValueDateValidation
*
* */
public class ForwardValueDateValidation implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Trade date is not before value date (forward).";

    @Override
    public ValidationResult validate(final Trade data) {
        return data.getValueDate().isAfter(data.getTradeDate())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
