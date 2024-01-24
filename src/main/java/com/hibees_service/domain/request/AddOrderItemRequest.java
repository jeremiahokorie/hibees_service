package com.hibees_service.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddOrderItemRequest {
    private Long menuItemId;
    private int quantity;

}
