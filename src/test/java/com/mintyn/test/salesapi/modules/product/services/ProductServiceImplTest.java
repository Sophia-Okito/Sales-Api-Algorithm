package com.mintyn.test.salesapi.modules.product.services;

import com.mintyn.test.salesapi.dtos.StandardResponse;
import com.mintyn.test.salesapi.enums.Status;
import com.mintyn.test.salesapi.exceptions.NotFoundException;
import com.mintyn.test.salesapi.modules.product.dtos.CreateProductDTO;
import com.mintyn.test.salesapi.modules.product.models.Product;
import com.mintyn.test.salesapi.modules.product.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private static final String PRODUCT_NAME = "Diamond Ring";
    private static final Long PRODUCT_ID = 1L;


    @Test
    void creatProduct() {
        when(productRepository.save(any())).thenReturn(getProduct());
        StandardResponse response = productService.creatProduct(getProductDto());
        assertEquals(Status.CREATED, response.getStatus());

    }


    @Test
    void getProductByIdFails() {
        when(productRepository.findByProductId(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(NotFoundException.class, () -> productService.getProductById(PRODUCT_ID));
        assertNotNull(exception);

    }

    @Test
    void getProductById() {
        when(productRepository.findByProductId(any())).thenReturn(Optional.of(getProduct()));
        StandardResponse response = productService.getProductById(PRODUCT_ID);
        assertEquals(Status.SUCCESS, response.getStatus());

    }


    private Product getProduct() {
        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setId(PRODUCT_ID);
        return product;
    }

    private CreateProductDTO getProductDto() {
        CreateProductDTO dto = new CreateProductDTO();
        dto.setName(PRODUCT_NAME);
        dto.setDescription("This is a diamond ring");
        dto.setQuantity(4);
        dto.setUnitPrice(new BigDecimal(100));
        return dto;
    }
}