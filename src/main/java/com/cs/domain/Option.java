package com.cs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Option extends Trade {

    @NotNull
    private String style;
    @NotNull
    private String strategy;
    @NotNull
    private String payCcy;
    @NotNull
    private BigDecimal premium;
    @NotNull
    private String premiumCcy;
    @NotNull
    private String premiumType;
    @NotNull
    private LocalDate premiumDate;
    @NotNull
    private LocalDate deliveryDate;
    @NotNull
    private LocalDate expiryDate;
    private LocalDate excerciseStartDate;

}
