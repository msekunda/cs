package com.cs.validation.condition.spot;

import com.cs.domain.Trade;
import com.cs.validation.ValidationResult;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SpotValueDateValidationTest {

    @Test
    public void shouldReturnValidWhenValueDateIsAfterTradeDateAndDifferenceBetweenValueAndTradeIsTwoDays() {
        //given
        final Trade spot = mock(Trade.class);
        when(spot.getValueDate()).thenReturn(LocalDate.of(2017, 1, 13));
        when(spot.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 11));
        //when
        final ValidationResult result = new SpotValueDateValidation().validate(spot);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnNotValidWhenValueDateIsAfterTradeDateAndDifferenceBetweenValueAndTradeIsThreeDays() {
        //given
        final Trade spot = mock(Trade.class);
        when(spot.getValueDate()).thenReturn(LocalDate.of(2017, 1, 13));
        when(spot.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 10));
        //when
        final ValidationResult result = new SpotValueDateValidation().validate(spot);

        //then
        assertThat(result.isValid()).isFalse();
    }

}