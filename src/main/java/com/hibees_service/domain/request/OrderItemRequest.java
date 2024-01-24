package com.hibees_service.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemRequest {

    private Long menuItemId;
    private int quantity;


    public OrderItemRequest() {
    }

    public OrderItemRequest(Long menuItemId, int quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }
}
