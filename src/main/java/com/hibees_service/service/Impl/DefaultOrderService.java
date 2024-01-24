package com.hibees_service.service.Impl;

import com.hibees_service.domain.request.AddOrderItemRequest;
import com.hibees_service.domain.request.OrderItemRequest;
import com.hibees_service.domain.request.OrderRequest;
import com.hibees_service.dto.enums.OrderStatus;
import com.hibees_service.persistence.admin.MenuItems;
import com.hibees_service.persistence.entity.Order;
import com.hibees_service.persistence.entity.OrderItem;
import com.hibees_service.persistence.entity.User;
import com.hibees_service.persistence.repository.MenuItemRepository;
import com.hibees_service.persistence.repository.OrderRepository;
import com.hibees_service.persistence.repository.UserRepository;
import com.hibees_service.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public Order addItemsToOrder(Long orderId, List<AddOrderItemRequest> addOrderItemRequests) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = getOrderById(orderId);
        if (Objects.equals(order.getStatus(), String.valueOf(OrderStatus.PLACED))) {
            // You may want to implement business logic for canceling orders based on status, time, etc.
            order.setStatus(String.valueOf(OrderStatus.CANCELED));
            orderRepository.save(order);
        } else {
            throw new IllegalStateException("Cannot cancel order with status: " + order.getStatus());
        }
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        return null;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return null;
    }

    @Transactional
    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        // Retrieve user from the database (assuming user ID is provided in the request)
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Validate menu items and calculate total amount
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderRequest.getItems()) {
            MenuItems menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Menu item not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(BigDecimal.valueOf(menuItem.getPrice()));

            totalAmount = totalAmount.add(orderItem.getPrice());
            orderItems.add(orderItem);
        }

        // Create the order
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);
        order.setStatus(String.valueOf(OrderStatus.PLACED));

        // Save the order to the database
        return orderRepository.save(order);
    }
}

