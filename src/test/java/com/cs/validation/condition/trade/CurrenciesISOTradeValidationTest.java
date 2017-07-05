package com.cs.validation.condition.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationResult;
import com.cs.validation.service.CurrenciesPairConverter;
import com.cs.validation.service.CurrencyISO;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrenciesISOTradeValidationTest {

    @Test
    public void shouldReturnValidWhenCurrenciesHaveValidISO() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCcyPair()).thenReturn("EURUSD");
        //when
        ValidationResult result = new CurrenciesISOTradeValidation(new CurrencyISO(), new CurrenciesPairConverter()).validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenCurrenciesHaveNotValidData() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCcyPair()).thenReturn("EU");
        //when
        final CurrenciesISOTradeValidation currenciesISOTradeValidation = new CurrenciesISOTradeValidation(new CurrencyISO(), new CurrenciesPairConverter());
        assertThatThrownBy(() -> currenciesISOTradeValidation.validate(trade))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldReturnInvalidWhenCurrenciesHaveNotValidISOData() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getCcyPair()).thenReturn("EURQWE");
        //when
        ValidationResult result = new CurrenciesISOTradeValidation(new CurrencyISO(), new CurrenciesPairConverter()).validate(trade);

        //then
        assertThat(result.isValid()).isFalse();
    }
}