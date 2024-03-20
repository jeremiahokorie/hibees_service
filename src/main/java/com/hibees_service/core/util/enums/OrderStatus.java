package com.hibees_service.core.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING, SUCCESS, FAILED, PLACED, CANCELED;
}
