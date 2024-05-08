package com.scaler.productService.controllers;

import com.scaler.productService.dtos.AllCategoriesResponseDto;
import com.scaler.productService.dtos.ProductListRequestDto;
import com.scaler.productService.dtos.ProductListResponseDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{uuid}")
    public void getCategory(@PathVariable("uuid") String uuid) {
        categoryService.getCategory(uuid);
    }

    @GetMapping("/products")
    public List<ProductListResponseDto> getProductsList(@RequestBody ProductListRequestDto productListRequestDto) {
        return categoryService.getProductsList(productListRequestDto.getCategoryUuids());
    }

    @GetMapping("/all")
    public AllCategoriesResponseDto getAllCategories() throws NotFoundException {
        return categoryService.getAllCategories();
    }

    @GetMapping("/products/{category}")
    public List<ProductListResponseDto> getProductsList(@PathVariable("category") String category) throws NotFoundException {
        return categoryService.getProductsList(category);
    }
}
