package com.mintyn.test.salesapi.modules.product.services;

import com.mintyn.test.salesapi.dtos.StandardResponse;
import com.mintyn.test.salesapi.enums.Status;
import com.mintyn.test.salesapi.exceptions.NotFoundException;
import com.mintyn.test.salesapi.modules.product.dtos.CreateProductDTO;
import com.mintyn.test.salesapi.modules.product.dtos.UpdateProductDTO;
import com.mintyn.test.salesapi.modules.product.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import com.mintyn.test.salesapi.modules.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StandardResponse creatProduct(CreateProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setQuantity(dto.getQuantity());
        product.setUnitPrice(dto.getUnitPrice());
        product.setCreatedAt(LocalDateTime.now());
        product =  productRepository.save(product);
        return new StandardResponse(Status.CREATED, product);
    }

    @Override
    public StandardResponse updateProduct(Long id, UpdateProductDTO dto) {
        Optional<Product> optionalProduct = productRepository.findByProductId(id);
        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        Product product = optionalProduct.get();
        product.setUnitPrice(dto.getUnitPrice());
        product.setUpdatedAt(LocalDateTime.now());
        product = productRepository.save(product);

        return new StandardResponse(Status.SUCCESS, product);
    }

    @Override
    public StandardResponse getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findByProductId(id);
        if (optionalProduct.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        return new StandardResponse(Status.SUCCESS, optionalProduct.get());
    }

    @Override
    public StandardResponse getAllProduct(String name, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        LocalDateTime start = Objects.nonNull(startDate) ? LocalDateTime.of(startDate, LocalTime.MIN) : null;
        LocalDateTime end = Objects.nonNull(endDate) ? LocalDateTime.of(endDate, LocalTime.MAX) : null;

        if (Objects.isNull(start) && Objects.isNull(end)  && Objects.isNull(name)) {
            Page<Product> products = productRepository.findAll(pageable);
            if(products.isEmpty()){
                return new StandardResponse(Status.NO_CONTENT);
            }

            return new StandardResponse(Status.SUCCESS, products);
        }


        CriteriaBuilder qb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> cq = qb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = getPredicates(start, end, name, qb, root, cq);

        cq.select(root).where(predicates.toArray(new Predicate[]{}));

        List<Product> res = entityManager.createQuery(cq).getResultList();

        if (res.isEmpty()) {
            return new StandardResponse(Status.NO_CONTENT);
        }

        return new StandardResponse(Status.SUCCESS, new PageImpl<>(res, pageable, res.size()));

    }

    private List<Predicate> getPredicates(LocalDateTime start, LocalDateTime end, String name, CriteriaBuilder qb, Root<Product> root, CriteriaQuery<Product> cq) {

        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(start)) {
            predicates.add(qb.greaterThanOrEqualTo(root.get("createdAt"), start));
        }

        if (Objects.nonNull(end)) {
            predicates.add(qb.lessThan(root.get("createdAt"), end));
        }

        if (Objects.nonNull(name)) {
            predicates.add(qb.like(root.get("name"), "%" + name + "%"));
        }

        return predicates;
    }
}
