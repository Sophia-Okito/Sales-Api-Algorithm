package com.mintyn.test.salesapi.modules.order.dtos;

import com.mintyn.test.salesapi.annotations.ValidProduct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class OrderDto implements Serializable {

    @NotBlank(message = "customer name is required")
    private String customerName;

    @NotBlank(message = "customer phoneNumber is required")
    private String customerPhoneNumber;

    private Set<@Valid OrderItemDto> items = new HashSet<>();

    @Setter
    @Getter
    @ValidProduct(productId = "id", quantity = "quantity")
    public static class OrderItemDto implements Serializable {
        @NotNull(message = "product id is required")
        private Long id;

        @Min(value = 1, message = "quantity must be greater than 0")
        private int quantity = 1;
    }
}
