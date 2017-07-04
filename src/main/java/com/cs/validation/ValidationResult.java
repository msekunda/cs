package com.cs.validation;

import lombok.Getter;

@Getter
public class ValidationResult {

    private final boolean isValid;
    private final String validationInfo;

    private ValidationResult(final boolean isValid, final String validationInfo) {
        this.isValid = isValid;
        this.validationInfo = validationInfo;
    }

    public static ValidationResult valid() {
        return new ValidationResult(true, "");
    }

    public static ValidationResult invalid(final String validationInfo) {
        return new ValidationResult(false, validationInfo);
    }

}
