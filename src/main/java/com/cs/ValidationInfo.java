package com.cs;


import com.cs.domain.Trade;
import lombok.Getter;

@Getter
class ValidationInfo {
    private final Trade trade;
    private final boolean valid;
    private final String error;

    public ValidationInfo(final Trade trade, final boolean valid, final String error) {
        this.trade = trade;
        this.valid = valid;
        this.error = error;
    }
}
