package com.cs.validation.condition.trade;


import com.cs.domain.Trade;
import com.cs.validation.ValidationResult;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TradeDateBeforeValueDateValidationTest {

    @Test
    public void shouldReturnDatesAreValidWhenValueDateIsAfterTradeDate() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getValueDate()).thenReturn(LocalDate.of(2017, 1, 17));
        when(trade.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));

        //when
        final ValidationResult result = new TradeDateBeforeValueDateValidation().validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnDatesAreInvalidWhenValueDateIsBeforeTradeDate() {
        //given
        final Trade trade = mock(Trade.class);
        when(trade.getValueDate()).thenReturn(LocalDate.of(2017, 1, 10));
        when(trade.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));

        //when
        final ValidationResult result = new TradeDateBeforeValueDateValidation().validate(trade);

        //then
        assertThat(result.isValid()).isFalse();
    }
}