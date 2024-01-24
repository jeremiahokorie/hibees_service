package com.hibees_service.service;

import com.hibees_service.domain.request.AddOrderItemRequest;
import com.hibees_service.domain.request.OrderRequest;
import com.hibees_service.persistence.entity.Order;

import java.util.List;

public interface OrderService {

    Order addItemsToOrder(Long orderId, List<AddOrderItemRequest> addOrderItemRequests);

    void cancelOrder(Long orderId);

    List<Order> getOrderHistory(Long userId);

    Order getOrderById(Long orderId);

    Order placeOrder(OrderRequest orderRequest);
}
