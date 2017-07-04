package com.cs.validation.condition.spot;

import com.cs.domain.Spot;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class SpotValueDateValidation implements ValidationCondition<Spot> {

    @Override
    public ValidationResult validate(final Spot data) {
        //TODO
        return ValidationResult.valid();
    }
}
