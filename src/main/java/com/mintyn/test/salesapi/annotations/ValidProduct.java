package com.mintyn.test.salesapi.annotations;

import com.mintyn.test.salesapi.validators.ValidProductValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ValidProductValidator.class})
@Retention(RUNTIME)
@Target({ElementType.TYPE})
public @interface ValidProduct {
    String message() default  "product out of stock or does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String productId();

    String quantity();


}
