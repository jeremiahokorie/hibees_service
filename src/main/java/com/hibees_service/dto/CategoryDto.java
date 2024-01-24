package com.hibees_service.dto;

import com.hibees_service.persistence.admin.Category;
import com.hibees_service.persistence.entity.User;
import lombok.Data;

import java.util.Date;

@Data
public class CategoryDto {
    private String name;
    private Date createdAt;

    public static CategoryDto fromEntity(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setCreatedAt(new Date());
        return categoryDto;
    }
}
