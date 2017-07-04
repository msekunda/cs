package com.cs.validation.option;

import com.cs.domain.Option;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class ExpiryDateAndPremiumDateShallBeBeforeDeliveryDate implements ValidationCondition<Option> {

    private static final String VALIDATION_ERROR_MESSAGE = "Expiry date or premium data is not before delivery date.";

    @Override
    public ValidationResult validate(final Option data) {
        return data.getExpiryDate().isBefore(data.getDeliveryDate()) && data.getPremiumDate().isBefore(data.getDeliveryDate())
                ? ValidationResult.valid()
                : ValidationResult.invalid(VALIDATION_ERROR_MESSAGE);
    }
}
