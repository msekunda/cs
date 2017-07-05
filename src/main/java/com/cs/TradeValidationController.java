package com.cs;

import com.cs.domain.Trade;
import com.cs.domain.TradesWrapper;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import javaslang.Tuple;
import javaslang.Tuple2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static javaslang.API.*;

@RestController
public class TradeValidationController {

    private final List<ValidationCondition> validationConditions;
    private final List<ValidationCondition> optionConditions;
    private final List<ValidationCondition> spotConditions;
    private final List<ValidationCondition> forwardConditions;

    public TradeValidationController(@Qualifier("trades") List<ValidationCondition> validationConditions,
                                     @Qualifier("options") List<ValidationCondition> optionConditions,
                                     @Qualifier("spots") List<ValidationCondition> spotConditions,
                                     @Qualifier("forwards") List<ValidationCondition> forwardConditions) {
        this.validationConditions = validationConditions;
        this.optionConditions = optionConditions;
        this.spotConditions = spotConditions;
        this.forwardConditions = forwardConditions;
    }

    @RequestMapping(value = "/validation/trade", method = RequestMethod.POST)
    ResponseEntity<?> validateTrade(@Valid @RequestBody final TradesWrapper tradesWrapper) {
        final Stream<Tuple2<Trade, Stream<ValidationResult>>> specificValidation = tradesWrapper.getTrades().stream()
                                                                                                .map(x -> Match(x.getType()).of(
                                                                                                        Case($("VanillaOption"), validateOption(x)),
                                                                                                        Case($("Forward"), validateForward(x)),
                                                                                                        Case($("Spot"), validateTrade(x))));

        final List<Tuple2<Trade, String>> allValidationResults = specificValidation.map(x -> Tuple.of(x._1, concat(x._2, tradesValidation(x))))
                                                                                   .map(x -> Tuple.of(x._1, prepareValidationMessage(x)))
                                                                                   .collect(toList());

        return ResponseEntity.ok(convertToValidationInfo(allValidationResults));
    }

    private List<ValidationInfo> convertToValidationInfo(List<Tuple2<Trade, String>> validationResults) {
        return validationResults.stream().map(x -> new ValidationInfo(x._1, x._2.isEmpty(), x._2)).collect(toList());
    }

    private String prepareValidationMessage(Tuple2<Trade, Stream<ValidationResult>> validationResults) {
        return validationResults._2.filter(y -> !y.isValid()).map(x -> x.getValidationInfo()).collect(joining(" "));
    }

    private Stream<ValidationResult> tradesValidation(Tuple2<Trade, Stream<ValidationResult>> validationResults) {
        return validationConditions.stream().map(x -> x.validate(validationResults._1));
    }

    private Tuple2<Trade, Stream<ValidationResult>> validateTrade(Trade trade) {
        return Tuple.of(trade, spotConditions.stream().map(x -> x.validate(trade)));
    }

    private Tuple2<Trade, Stream<ValidationResult>> validateForward(Trade trade) {
        return Tuple.of(trade, forwardConditions.stream().map(x -> x.validate(trade)));
    }

    private Tuple2<Trade, Stream<ValidationResult>> validateOption(Trade trade) {
        return Tuple.of(trade, optionConditions.stream().map(x -> x.validate(trade)));
    }

}
