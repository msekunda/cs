package com.cs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Trade {

    @NotNull
    private final String customer;
    @NotNull
    private final String ccyPair;
    @NotNull
    private final DIRECTION direction;
    @NotNull
    private final LocalDate tradeDate;
    @NotNull
    private final BigDecimal amount1;
    @NotNull
    private final BigDecimal amount2;
    @NotNull
    private final LocalDate valueDate;
    @NotNull
    private final String legalEntity;
    @NotNull
    private final String trader;
    @NotNull
    private final String type;
    private final BigDecimal rate;
    private final String style;
    private final String strategy;
    private final String payCcy;
    private final BigDecimal premium;
    private final String premiumCcy;
    private final String premiumType;
    private final LocalDate premiumDate;
    private final LocalDate deliveryDate;
    private final LocalDate expiryDate;
    private final LocalDate excerciseStartDate;


    enum DIRECTION {BUY, SELL}

}
