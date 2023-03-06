package com.mintyn.test.salesapi.validators;

import com.mintyn.test.salesapi.annotations.ValidProduct;
import com.mintyn.test.salesapi.modules.product.models.Product;

import com.mintyn.test.salesapi.modules.product.repositories.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidProductValidator implements ConstraintValidator<ValidProduct, Object> {
    private final ProductRepository productRepository;

    private String quantity;

    private String productId;


    @Override
    public void initialize(final ValidProduct annotation) {
        quantity = annotation.quantity();
        productId = annotation.productId();
    }


    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(value);

        Long id;
        try {
            id = (Long) wrapper.getPropertyValue(productId);
        } catch (Exception e) {
            return false;
        }

        if(id == null){
            return true;
        }

        Integer qty;
        try {
            qty = (Integer) wrapper.getPropertyValue(quantity);
        }catch (Exception e) {
            return false;
        }



        try {
            return isPresent(id, qty);
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean isPresent(Long id, Integer qty) throws SQLException {
        Optional<Product> optionalProduct = productRepository.findByProductId(id);
        if (optionalProduct.isEmpty()) {
            return false;
        }

        return optionalProduct.get().getQuantity() >= qty;

    }


}
