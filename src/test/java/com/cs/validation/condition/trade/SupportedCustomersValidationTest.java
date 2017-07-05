package com.cs.validation.condition.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupportedCustomersValidationTest {


    @Test
    public void shouldReturnNotValidWhenCustomerIsNotSupported() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCustomer()).thenReturn("dummyCustomer");

        //when
        ValidationResult result = new SupportedCustomersValidation().validate(trade);

        //then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void shouldReturnValidWhenCustomerIsSupported() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCustomer()).thenReturn("PLUTO2");

        //when
        ValidationResult result = new SupportedCustomersValidation().validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }
}