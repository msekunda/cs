package com.cs.validation.condition.option;

import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;

public class ExpiryDateAndPremiumDateShallBeBeforeDeliveryDate implements ValidationCondition {

    private static final String VALIDATION_ERROR_MESSAGE = "Expiry date or premium data is not before delivery date.";

    @Override
    public ValidationResult validate(final Trade data) {
        return data.getExpiryDate().isBefore(data.getDeliveryDate()) && data.getPremiumDate().isBefore(data.getDeliveryDate())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
