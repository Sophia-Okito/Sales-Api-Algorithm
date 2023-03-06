package com.mintyn.test.salesapi.modules.product.dtos;

import com.mintyn.test.salesapi.annotations.PositiveBigDecimal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductDTO {

    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @Min(value = 1, message = "name is required")
    private int quantity;

    @PositiveBigDecimal
    @NotNull(message = "unit price is required")
    private BigDecimal unitPrice;

}
