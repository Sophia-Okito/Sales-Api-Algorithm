package com.mintyn.test.salesapi.annotations;

import com.mintyn.test.salesapi.validators.PositiveBigDecimalValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {PositiveBigDecimalValidator.class})
@Retention(RUNTIME)
@Target({ElementType.FIELD})
public @interface PositiveBigDecimal {

    String message() default "value must be a positive value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}

