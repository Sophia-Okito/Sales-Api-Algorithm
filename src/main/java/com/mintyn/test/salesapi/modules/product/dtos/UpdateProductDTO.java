package com.mintyn.test.salesapi.modules.product.dtos;

import com.mintyn.test.salesapi.annotations.PositiveBigDecimal;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductDTO {

    @NotNull(message =  "unit price is required")
    @PositiveBigDecimal
    private BigDecimal unitPrice;

}
