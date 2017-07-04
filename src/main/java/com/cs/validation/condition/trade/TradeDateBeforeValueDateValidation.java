package com.cs.validation.condition.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class TradeDateBeforeValueDateValidation implements ValidationCondition<Trade> {

    private static final String VALIDATION_ERROR_MESSAGE = "Trade date is not before value date.";

    @Override
    public ValidationResult validate(final Trade data) {
        return data.getValueDate().isAfter(data.getTradeDate())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
