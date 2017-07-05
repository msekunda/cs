package com.cs.validation;

import com.cs.domain.Trade;

public interface ValidationCondition {

    ValidationResult validate(final Trade data);

}
