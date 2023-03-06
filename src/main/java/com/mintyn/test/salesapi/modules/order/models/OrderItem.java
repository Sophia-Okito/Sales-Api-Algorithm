package com.mintyn.test.salesapi.modules.order.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mintyn.test.salesapi.modules.product.models.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Order order;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private int quantity;

    private Date createdAt;

    private Date updatedAt;

}
