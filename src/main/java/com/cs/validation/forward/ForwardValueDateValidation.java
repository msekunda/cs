package com.cs.validation.forward;

import com.cs.domain.Forward;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class ForwardValueDateValidation implements ValidationCondition<Forward> {

    @Override
    public ValidationResult validate(final Forward data) {
        //TODO
        return ValidationResult.valid();
    }
}
