package com.mintyn.test.salesapi.modules.product.controllers;

import com.mintyn.test.salesapi.controllers.BaseController;
import com.mintyn.test.salesapi.dtos.StandardResponse;
import com.mintyn.test.salesapi.modules.product.dtos.CreateProductDTO;
import com.mintyn.test.salesapi.modules.product.dtos.UpdateProductDTO;
import com.mintyn.test.salesapi.modules.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController extends BaseController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<StandardResponse> createProduct(@RequestBody @Valid CreateProductDTO dto) {
        return updateResponseStatus(productService.creatProduct(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StandardResponse> updateProductPrice(@PathVariable Long id, @RequestBody @Valid UpdateProductDTO dto) {
        return updateResponseStatus(productService.updateProduct(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getProductById(@PathVariable Long id) {
        return updateResponseStatus(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllProduct(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                                          @RequestParam(required = false) String name,
                                                          @PageableDefault(size = 7 ) @SortDefault.SortDefaults({
                                                                  @SortDefault(sort = "name", direction = Sort.Direction.ASC),
                                                                  @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC)
                                                          }) Pageable pageable) {
        return updateResponseStatus(productService.getAllProduct(name, start, end, pageable));
    }

}
