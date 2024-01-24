package com.hibees_service.domain.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    private Long userId;
    private List<AddOrderItemRequest> orderItems;

    public OrderItemRequest[] getItems() {
        return new OrderItemRequest[0];
    }
}
