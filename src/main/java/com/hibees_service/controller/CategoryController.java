package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.domain.request.CategoryRequest;
import com.hibees_service.dto.ApiResponse;
import com.hibees_service.dto.CategoryDto;
import com.hibees_service.persistence.admin.Category;
import com.hibees_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ApiResponse<?> category(@RequestBody CategoryRequest categoryRequest){
        CategoryDto categoryDto = categoryService.createCategory(categoryRequest);
        return ApiResponse.success(categoryDto);
    }

    @GetMapping("/findCategory")
    public ApiResponse<?> category(){
        List<Category> cat = categoryService.categories();
        return ApiResponse.success(cat);
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<?> findById(@PathVariable("categoryId") Long id) {
        Category category = categoryService.findCategoryById(id);
        return ApiResponse.success(category);
    }

    @GetMapping("/category/{categoryName}")
    public ApiResponse<?> findByCategoryName(@PathVariable("categoryName") String name){
        Category categoryName = categoryService.findByCategoryName(name);
            return ApiResponse.success(categoryName);
    }


}
