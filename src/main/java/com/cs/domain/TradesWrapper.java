package com.cs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import java.util.List;

@Getter
@AllArgsConstructor
public class TradesWrapper {

    @Valid
    @JsonProperty("trades")
    private List<Trade> trades;
}
