package com.hibees_service.domain.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CategoryRequest {
    private String name;
    private Date createdAt;
}
