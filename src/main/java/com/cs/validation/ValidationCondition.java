package com.cs.validation;

public interface ValidationCondition<T> {

    ValidationResult validate(final T data);

}
