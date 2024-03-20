package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.domain.request.AddOrderItemRequest;
import com.hibees_service.domain.request.OrderRequest;
import com.hibees_service.persistence.entity.Order;
import com.hibees_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    // Endpoint to place a new order
    @PostMapping("/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        // Assuming OrderRequest is a DTO containing order details
        Order order = orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    // Endpoint to get details of a specific order
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    // Endpoint to get the order history for a user
    @GetMapping("/history")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestParam Long userId) {
        List<Order> orderHistory = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(orderHistory);
    }

    // Endpoint to cancel a specific order
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order canceled successfully.");
    }

    // Endpoint to add items to an existing order
    @PostMapping("/{orderId}/add-items")
    public ResponseEntity<Order> addItemsToOrder(@PathVariable Long orderId,@RequestBody List<AddOrderItemRequest> addOrderItemRequests) {
        Order updatedOrder = orderService.addItemsToOrder(orderId, addOrderItemRequests);
        return ResponseEntity.ok(updatedOrder);
    }

}
