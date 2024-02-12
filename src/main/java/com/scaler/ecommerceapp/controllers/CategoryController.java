package com.scaler.ecommerceapp.controllers;

import com.scaler.ecommerceapp.dtos.ProductListRequestDto;
import com.scaler.ecommerceapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping("{uuid}")
    public void getCategory(@PathVariable("uuid") String uuid) {
        categoryService.getCategory(uuid);
    }
    @GetMapping
    public void getProductsList(@RequestBody ProductListRequestDto productListRequestDto) {
        categoryService.getProductsList(productListRequestDto.getCategoryUuids());

    }
}
