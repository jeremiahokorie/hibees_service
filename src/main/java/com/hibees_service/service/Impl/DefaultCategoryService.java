package com.hibees_service.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibees_service.core.exceptions.CustomException;
import com.hibees_service.domain.request.CategoryRequest;
import com.hibees_service.dto.CategoryDto;
import com.hibees_service.persistence.admin.Category;
import com.hibees_service.persistence.repository.CategoryRepository;
import com.hibees_service.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DefaultCategoryService implements CategoryService {
    private CategoryRepository categoryRepository;
    ModelMapper modelMapper;
   private ObjectMapper objectMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryRequest) {
        log.info("Creating category {}", categoryRequest.getName());
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .build();
        categoryRepository.save(category);
        return fromEntity(category);
    }

    @Override
    public List<Category> categories() {
     return categoryRepository.findAll();
    }

    @Override
    public CategoryDto findCategoryById(Long id) {
        categoryRepository.findById(id).orElseThrow(() -> new CustomException("Category with  "+ id +" not found"));
        return fromEntity(categoryRepository.findById(id).get());
    }


    private  CategoryDto fromEntity(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @Override
    public CategoryDto findName(String name) {
        Category category = categoryRepository.findByname(name)
                .orElseThrow(() -> new CustomException("Category with  "+ name +" not found"));
        return fromEntity(category);
    }

}
