package com.cs.validation;

import com.cs.domain.Forward;
import com.cs.validation.condition.trade.SupportedCustomersValidation;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SupportedCustomersValidationTest {


    @Test
    public void shouldReturnNotValidWhenCustomerIsNotSupported() {
        //given
        final Forward forward = mock(Forward.class);
        when(forward.getCustomer()).thenReturn("dummyCustomer");

        //when
        ValidationResult result = new SupportedCustomersValidation().validate(forward);

        //then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void shouldReturnValidWhenCustomerIsSupported() {
        //given
        final Forward forward = mock(Forward.class);
        when(forward.getCustomer()).thenReturn("PLUTO2");

        //when
        ValidationResult result = new SupportedCustomersValidation().validate(forward);

        //then
        assertThat(result.isValid()).isTrue();
    }
}