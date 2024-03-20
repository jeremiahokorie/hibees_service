package com.hibees_service.dto;

import com.hibees_service.persistence.admin.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private Date createdAt;

    private ModelMapper modelMapper;

//    public static CategoryDto fromEntity(Category category){
//        CategoryDto categoryDto = new CategoryDto();
//        categoryDto = modelMapper.map(category, CategoryDto.class);
//        return categoryDto;
//    }
}
