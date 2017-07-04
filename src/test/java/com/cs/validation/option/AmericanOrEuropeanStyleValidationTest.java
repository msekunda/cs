package com.cs.validation.option;

import com.cs.domain.Option;
import com.cs.validation.ValidationResult;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AmericanOrEuropeanStyleValidationTest {


    @Test
    public void shouldReturnValidWhenStyleIsEuropean() {
        //given
        final Option option = mock(Option.class);
        when(option.getStyle()).thenReturn("EUROPEAN");

        //when
        final ValidationResult result = new AmericanOrEuropeanStyleValidation().validate(option);

        //then
        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldReturnInvalidWhenStyleIsNotSupported() {
        //given
        final Option option = mock(Option.class);
        when(option.getStyle()).thenReturn("DUMMY");

        //when
        final ValidationResult result = new AmericanOrEuropeanStyleValidation().validate(option);

        //then
        assertThat(result.isValid()).isFalse();
    }
}