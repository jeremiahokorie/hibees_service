package com.hibees_service.persistence.entity;

import com.hibees_service.persistence.admin.MenuItems;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.awt.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItem_Id;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItems menuItem;

    private int quantity;
}
