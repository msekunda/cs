package com.cs.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Forward.class, name = "Forward"),
        @JsonSubTypes.Type(value = Spot.class, name = "Spot"),
        @JsonSubTypes.Type(value = Option.class, name = "VanillaOption")
})
@Getter
@Setter
public abstract class Trade {

    @NotNull
    private String customer;
    @NotNull
    private String ccyPair;
    @NotNull
    private DIRECTION direction;
    @NotNull
    private LocalDate tradeDate;
    @NotNull
    private BigDecimal amount1;
    @NotNull
    private BigDecimal amount2;
    @NotNull
    private LocalDate valueDate;
    @NotNull
    private String legalEntity;
    @NotNull
    private String trader;
    @NotNull
    private BigDecimal rate;

    enum DIRECTION {BUY, SELL}

}
