package com.hibees_service.controller;

import com.hibees_service.core.constant.AppConstant;
import com.hibees_service.domain.request.CategoryRequest;
import com.hibees_service.dto.ApiResponse;
import com.hibees_service.dto.CategoryDto;
import com.hibees_service.persistence.admin.Category;
import com.hibees_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AppConstant.APP_CONTEXT + "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ApiResponse<CategoryDto> category(@RequestBody CategoryDto category){
        return ApiResponse.success(categoryService.createCategory(category));
    }

    @GetMapping("/find")
    public ApiResponse<?> category(){
        List<Category> cat = categoryService.categories();
        return ApiResponse.success(cat);
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryDto> findById(@PathVariable Long id) {
        return ApiResponse.success(categoryService.findCategoryById(id));
    }

//    @GetMapping("/{categoryId}")
//    public ResponseEntity<CategoryDto>findByName(@PathVariable String name){
//        return ResponseEntity.ok(categoryService.findName(name));
//    }


}
