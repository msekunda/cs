package com.cs.validation.condition.option;

import com.cs.domain.Option;
import com.cs.validation.ValidationResult;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExpiryDateAndPremiumDateShallBeBeforeDeliveryDateTest {

    @Test
    public void shouldReturnValidResultWhenExpiryDateAndPremiumDataIsBeforeDeliveryDate() {
        //given
        final Option option = mock(Option.class);
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 1, 10));
        when(option.getPremiumDate()).thenReturn(LocalDate.of(2017, 1, 11));
        when(option.getDeliveryDate()).thenReturn(LocalDate.of(2017, 1, 12));

        //when
        final ValidationResult result = new ExpiryDateAndPremiumDateShallBeBeforeDeliveryDate().validate(option);

        //then
        assertThat(result.isValid()).isTrue();

    }

    @Test
    public void shouldReturnInvalidResultWhenExpiryDateIsNotBeforeDeliveryDate() {
        //given
        final Option option = mock(Option.class);
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 1, 20));
        when(option.getPremiumDate()).thenReturn(LocalDate.of(2017, 1, 11));
        when(option.getDeliveryDate()).thenReturn(LocalDate.of(2017, 1, 12));

        //when
        final ValidationResult result = new ExpiryDateAndPremiumDateShallBeBeforeDeliveryDate().validate(option);

        //then
        assertThat(result.isValid()).isFalse();

    }

    @Test
    public void shouldReturnInvalidResultWhenPremiumDateIsNotBeforeDeliveryDate() {
        //given
        final Option option = mock(Option.class);
        when(option.getExpiryDate()).thenReturn(LocalDate.of(2017, 1, 10));
        when(option.getPremiumDate()).thenReturn(LocalDate.of(2017, 1, 20));
        when(option.getDeliveryDate()).thenReturn(LocalDate.of(2017, 1, 12));

        //when
        final ValidationResult result = new ExpiryDateAndPremiumDateShallBeBeforeDeliveryDate().validate(option);

        //then
        assertThat(result.isValid()).isFalse();

    }
}