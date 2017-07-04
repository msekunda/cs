package com.cs;

import com.cs.domain.Forward;
import com.cs.domain.Option;
import com.cs.domain.Spot;
import com.cs.domain.Trade;
import com.cs.validation.ValidationCondition;
import com.cs.validation.ValidationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.concat;

@RestController
public class TradeValidationController {

    private final List<ValidationCondition<Trade>> validationConditions;
    private final List<ValidationCondition<Option>> optionConditions;
    private final List<ValidationCondition<Spot>> spotConditions;
    private final List<ValidationCondition<Forward>> forwardConditions;

    public TradeValidationController(final List<ValidationCondition<Trade>> validationConditions,
                                     final List<ValidationCondition<Option>> optionConditions,
                                     final List<ValidationCondition<Spot>> spotConditions,
                                     final List<ValidationCondition<Forward>> forwardConditions) {
        this.validationConditions = validationConditions;
        this.optionConditions = optionConditions;
        this.spotConditions = spotConditions;
        this.forwardConditions = forwardConditions;
    }

    @RequestMapping(value = "/trade_validation/option", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<ValidationInfo> validateOption(@Valid @RequestBody final Option option) {
        final Stream<ValidationResult> optionsValidationResults = optionConditions.stream()
                                                                                  .map(x -> x.validate(option));
        return createValidationInfoForResults(concat(tradesValidationResults(option), optionsValidationResults));
    }

    private ResponseEntity<ValidationInfo> createValidationInfoForResults(final Stream<ValidationResult> validationResults) {
        final String errorsInfo = validationResults.filter(x -> !x.isValid())
                                                   .map(x -> x.getValidationInfo())
                                                   .collect(joining(" "));
        if (errorsInfo.isEmpty()) {
            return ResponseEntity.ok(ValidationInfo.ok());
        }
        return ResponseEntity.ok(ValidationInfo.error(errorsInfo));
    }

    private Stream<ValidationResult> tradesValidationResults(final Trade trade) {
        return validationConditions.stream().map(x -> x.validate(trade));
    }

    @RequestMapping(value = "/trade_validation/forward", method = RequestMethod.POST)
    ResponseEntity<ValidationInfo> validateForward(@Valid @RequestBody final Forward forward) {
        final Stream<ValidationResult> forwardsValidationResults = forwardConditions.stream().map(x -> x.validate(forward));
        return createValidationInfoForResults(concat(tradesValidationResults(forward), forwardsValidationResults));
    }

    @RequestMapping(value = "/trade_validation/spot", method = RequestMethod.POST)
    ResponseEntity<ValidationInfo> validateSpot(@Valid @RequestBody final Spot spot) {
        final Stream<ValidationResult> spotsValidationResults = spotConditions.stream().map(x -> x.validate(spot));
        return createValidationInfoForResults(concat(tradesValidationResults(spot), spotsValidationResults));
    }

}
