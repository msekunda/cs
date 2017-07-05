package com.cs.validation.condition.trade;

import com.cs.domain.Trade;
import com.cs.validation.ValidationResult;
import com.cs.validation.service.CurrenciesPairConverter;
import com.cs.validation.service.FixerClient;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValueDateIsNotWeekendOrNonWorkingDayTest {

    @Test
    public void shouldReturnValidWhenDatesForCurrenciesAreNotWeekendOrNonWorking() {
        //given
        final Trade trade = mock(Trade.class);
        final FixerClient fixer = mock(FixerClient.class);
        when(trade.getCcyPair()).thenReturn("EURUSD");
        when(trade.getValueDate()).thenReturn(LocalDate.of(2017, 7, 3));
        when(fixer.hasCurrencyRateForDate(any(), any())).thenReturn(true);

        //when
        final ValidationResult result = new ValueDateIsNotWeekendOrNonWorkingDay(fixer, new CurrenciesPairConverter()).validate(trade);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenDatesForCurrenciesAreWeekendOrNonWorking() {
        //given
        final Trade forward = mock(Trade.class);
        final FixerClient fixer = mock(FixerClient.class);
        when(forward.getCcyPair()).thenReturn("EURUSD");
        when(forward.getValueDate()).thenReturn(LocalDate.of(2017, 7, 3));
        when(fixer.hasCurrencyRateForDate(any(), any())).thenReturn(false);

        //when
        final ValidationResult result = new ValueDateIsNotWeekendOrNonWorkingDay(fixer, new CurrenciesPairConverter()).validate(forward);

        //then
        assertThat(result.isValid()).isFalse();
    }
}