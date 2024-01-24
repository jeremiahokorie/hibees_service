package com.hibees_service.service;

import com.hibees_service.domain.request.CategoryRequest;
import com.hibees_service.dto.CategoryDto;
import com.hibees_service.persistence.admin.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryRequest categoryRequest);


    List<Category> categories();

    Category findCategoryById(Long id);

    Category findByCategoryName(String name);
}
