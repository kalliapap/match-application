package com.match.dev.validator;

import org.springframework.util.CollectionUtils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;

public interface CommonValidator<T> {

    Validator getValidator();

    default void validate(T model) {
        Set<ConstraintViolation<T>> violations = getValidator().validate(model);
        if(!CollectionUtils.isEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }
    }
}
