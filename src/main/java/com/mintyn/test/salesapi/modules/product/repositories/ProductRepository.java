package com.mintyn.test.salesapi.modules.product.repositories;

import com.mintyn.test.salesapi.modules.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.id = :id")
    Optional<Product> findByProductId(@Param("id") Long id);
}
