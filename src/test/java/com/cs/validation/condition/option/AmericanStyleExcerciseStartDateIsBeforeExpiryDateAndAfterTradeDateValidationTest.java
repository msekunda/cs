package com.cs.validation.condition.option;

import com.cs.domain.Option;
import com.cs.validation.ValidationResult;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidationTest {

    @Test
    public void shouldReturnValidResultWhenExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDate() {
        //given
        final Option option = mock(Option.class);
        when(option.getStyle()).thenReturn("AMERICAN");
        when(option.getExcerciseStartDate()).thenReturn(LocalDate.of(2017, 1, 18));
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 1, 19));
        when(option.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));
        //when

        final ValidationResult result = new AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidation().validate(option);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidResultWhenExcerciseStartDateIsAfterExpiryDateAndAfterTradeDate() {
        //given
        final Option option = mock(Option.class);
        when(option.getStyle()).thenReturn("AMERICAN");
        when(option.getExcerciseStartDate()).thenReturn(LocalDate.of(2017, 1, 20));
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 1, 19));
        when(option.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 15));
        //when

        final ValidationResult result = new AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidation().validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void shouldReturnInvalidResultWhenExcerciseStartDateIsAfterExpiryDateAndBeforeTradeDate() {
        //given
        final Option option = mock(Option.class);
        when(option.getStyle()).thenReturn("AMERICAN");
        when(option.getExcerciseStartDate()).thenReturn(LocalDate.of(2017, 1, 21));
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 1, 19));
        when(option.getTradeDate()).thenReturn(LocalDate.of(2017, 1, 22));
        //when

        final ValidationResult result = new AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidation().validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }
}