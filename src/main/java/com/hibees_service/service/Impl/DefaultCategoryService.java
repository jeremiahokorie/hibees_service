package com.hibees_service.service.Impl;

import com.hibees_service.core.exceptions.CustomException;
import com.hibees_service.domain.request.CategoryRequest;
import com.hibees_service.dto.CategoryDto;
import com.hibees_service.persistence.admin.Category;
import com.hibees_service.persistence.repository.CategoryRepository;
import com.hibees_service.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultCategoryService implements CategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .createAt(new Date())
                .build();
        categoryRepository.save(category);
        return CategoryDto.fromEntity(category);
    }

    @Override
    public List<Category> categories() {
     return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Id does not exist"+ id));
        return category;
    }

    @Override
    public Category findByCategoryName(String name) {
        Category category = categoryRepository.findByname(name);
        if (category.getName() == null){
            throw new CustomException("Category not found");
        }
        return category;
    }

}
