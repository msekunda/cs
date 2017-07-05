package com.cs;

import com.cs.validation.ValidationCondition;
import com.cs.validation.condition.forward.ForwardValueDateValidation;
import com.cs.validation.condition.option.AmericanOrEuropeanStyleValidation;
import com.cs.validation.condition.option.AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidation;
import com.cs.validation.condition.option.CurrenciesISOOptionValidation;
import com.cs.validation.condition.option.ExpiryDateAndPremiumDateShallBeBeforeDeliveryDate;
import com.cs.validation.condition.spot.SpotValueDateValidation;
import com.cs.validation.condition.trade.CurrenciesISOTradeValidation;
import com.cs.validation.condition.trade.SupportedCustomersValidation;
import com.cs.validation.condition.trade.TradeDateBeforeValueDateValidation;
import com.cs.validation.condition.trade.ValueDateIsNotWeekendOrNonWorkingDay;
import com.cs.validation.service.CurrenciesPairConverter;
import com.cs.validation.service.CurrencyISO;
import com.cs.validation.service.FixerClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

    @Bean
    public javax.validation.Validator validator() {
        return new org.springframework.validation.beanvalidation.LocalValidatorFactoryBean();
    }

    @Bean
    public List<ValidationCondition> trades(CurrencyISO currencyISO, FixerClient fixerClient, CurrenciesPairConverter currenciesPairConverter) {
        return Arrays.asList(
                new CurrenciesISOTradeValidation(currencyISO, currenciesPairConverter),
                new SupportedCustomersValidation(),
                new TradeDateBeforeValueDateValidation(),
                new ValueDateIsNotWeekendOrNonWorkingDay(fixerClient, currenciesPairConverter));
    }

    @Bean
    public List<ValidationCondition> options(CurrencyISO currencyISO) {
        return Arrays.asList(
                new AmericanOrEuropeanStyleValidation(),
                new AmericanStyleExcerciseStartDateIsBeforeExpiryDateAndAfterTradeDateValidation(),
                new CurrenciesISOOptionValidation(currencyISO),
                new ExpiryDateAndPremiumDateShallBeBeforeDeliveryDate());
    }

    @Bean
    public List<ValidationCondition> forwards() {
        return Arrays.asList(new ForwardValueDateValidation());
    }

    @Bean
    public List<ValidationCondition> spots() {
        return Arrays.asList(new SpotValueDateValidation());
    }

}
