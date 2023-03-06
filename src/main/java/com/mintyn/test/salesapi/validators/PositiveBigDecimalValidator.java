package com.mintyn.test.salesapi.validators;

import com.mintyn.test.salesapi.annotations.PositiveBigDecimal;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class PositiveBigDecimalValidator implements ConstraintValidator<PositiveBigDecimal, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(bigDecimal)) {
            return true;
        }

        return bigDecimal.doubleValue() >= 0;
    }
}
