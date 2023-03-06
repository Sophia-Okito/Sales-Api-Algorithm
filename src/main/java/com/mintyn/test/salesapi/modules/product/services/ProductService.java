package com.mintyn.test.salesapi.modules.product.services;

import com.mintyn.test.salesapi.dtos.StandardResponse;
import com.mintyn.test.salesapi.modules.product.dtos.CreateProductDTO;
import com.mintyn.test.salesapi.modules.product.dtos.UpdateProductDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ProductService {

    StandardResponse creatProduct(CreateProductDTO dto);

    StandardResponse updateProduct(Long id, UpdateProductDTO dto);

    StandardResponse getProductById(Long id);

    StandardResponse getAllProduct(String name, LocalDate start, LocalDate end, Pageable pageable);
}
