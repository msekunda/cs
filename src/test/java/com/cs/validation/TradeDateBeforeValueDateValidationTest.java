package com.cs.validation;


import com.cs.domain.Forward;
import com.cs.validation.trade.TradeDateBeforeValueDateValidation;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TradeDateBeforeValueDateValidationTest {

    @Test
    public void shouldReturnDatesAreValidWhenValueDateIsAfterTradeDate() {
        //given
        final Forward forward = mock(Forward.class);
        when(forward.getValueDate()).thenReturn(LocalDate.of(2017, 1, 17));
        when(forward.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));

        //when
        final ValidationResult result = new TradeDateBeforeValueDateValidation().validate(forward);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnDatesAreInvalidWhenValueDateIsBeforeTradeDate() {
        //given
        final Forward forward = mock(Forward.class);
        when(forward.getValueDate()).thenReturn(LocalDate.of(2017, 1, 10));
        when(forward.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));

        //when
        final ValidationResult result = new TradeDateBeforeValueDateValidation().validate(forward);

        //then
        assertThat(result.isValid()).isFalse();
    }
}