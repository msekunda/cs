package com.cs.validation.condition.option;

import com.cs.domain.Trade;
import com.cs.validation.ValidationResult;
import com.cs.validation.service.CurrencyISO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrenciesISOOptionValidationTest {

    @Test
    public void shouldReturnValidWhenCurrencyIsISOValid() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getPayCcy()).thenReturn("USD");
        when(option.getPremiumCcy()).thenReturn("EUR");

        //when
        final ValidationResult result = new CurrenciesISOOptionValidation(new CurrencyISO()).validate(option);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenCurrencyIsNotISOValid() {
        //given
        final Trade option = mock(Trade.class);
        when(option.getPayCcy()).thenReturn("ASD");
        when(option.getPremiumCcy()).thenReturn("ZXC");

        //when
        final ValidationResult result = new CurrenciesISOOptionValidation(new CurrencyISO()).validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }
}