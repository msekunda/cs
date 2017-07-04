package com.cs.validation;

import com.cs.domain.Forward;
import com.cs.validation.service.CurrenciesPairConverter;
import com.cs.validation.service.CurrencyISO;
import com.cs.validation.trade.CurrenciesISOTradeValidation;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrenciesISOTradeValidationTest {

    @Test
    public void shouldReturnValidWhenCurrenciesHaveValidISO() {
        //given
        final Forward forward = mock(Forward.class);
        when(forward.getCcyPair()).thenReturn("EURUSD");
        //when
        ValidationResult result = new CurrenciesISOTradeValidation(new CurrencyISO(), new CurrenciesPairConverter()).validate(forward);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenCurrenciesHaveNotValidData() {
        //given
        final Forward forward = mock(Forward.class);
        when(forward.getCcyPair()).thenReturn("EU");
        //when
        final CurrenciesISOTradeValidation currenciesISOTradeValidation = new CurrenciesISOTradeValidation(new CurrencyISO(), new CurrenciesPairConverter());
        assertThatThrownBy(() -> currenciesISOTradeValidation.validate(forward))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldReturnInvalidWhenCurrenciesHaveNotValidISOData() {
        //given
        final Forward forward = mock(Forward.class);
        when(forward.getCcyPair()).thenReturn("EURQWE");
        //when
        ValidationResult result = new CurrenciesISOTradeValidation(new CurrencyISO(), new CurrenciesPairConverter()).validate(forward);

        //then
        assertThat(result.isValid()).isFalse();
    }
}