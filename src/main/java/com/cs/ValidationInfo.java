package com.cs;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
class ValidationInfo {
    private final boolean valid;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String error;

    private ValidationInfo(boolean valid, String error) {
        this.valid = valid;
        this.error = error;
    }

    static ValidationInfo ok() {
        return new ValidationInfo(true, null);
    }

    static ValidationInfo error(final String errorMessage) {
        return new ValidationInfo(false, errorMessage);
    }
}
