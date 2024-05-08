package com.scaler.productService.services;

import com.scaler.productService.Repositories.CategoryRepository;
import com.scaler.productService.dtos.AllCategoriesResponseDto;
import com.scaler.productService.dtos.ProductListResponseDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.models.Category;
import com.scaler.productService.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
//    @Transactional
    public void getCategory(String uuid) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(uuid));
        Category category = categoryOptional.get();
        System.out.println(category);
        List<Product> products = category.getProducts();
        System.out.println(products);
    }
    public List<ProductListResponseDto> getProductsList(String category) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findByName(category);
        if(optionalCategory.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        Category requestedCategory = optionalCategory.get();
        List<ProductListResponseDto> productListResponseDtos = new ArrayList<>();
        getProductListFromACategory(requestedCategory, productListResponseDtos);

        return productListResponseDtos;
    }

    private void getProductListFromACategory(Category requestedCategory, List<ProductListResponseDto> productListResponseDtos) {
        for(Product product: requestedCategory.getProducts()){
            ProductListResponseDto productListResponseDto = new ProductListResponseDto();
            productListResponseDto.setCategoryId(requestedCategory.getUuid().toString());
            productListResponseDto.setProductId(product.getUuid().toString());
            productListResponseDto.setTitle(product.getTitle());
            productListResponseDto.setPrice(product.getPrice().getPrice());
            productListResponseDto.setRating(product.getRating().getAverage());
            productListResponseDto.setImage(product.getImage());
            productListResponseDto.setCategory(requestedCategory.getName());
            productListResponseDto.setCurrency(product.getPrice().getCurrency());
            productListResponseDtos.add(productListResponseDto);
        }
    }

    public List<ProductListResponseDto> getProductsList(List<String> categoryUuids) {
        // Convert categoryUuids to UUID
        List<UUID> uuids = categoryUuids.stream().map(UUID::fromString).toList();

        List<Category> categories = categoryRepository.findAllById(uuids);
        List<ProductListResponseDto> productListResponseDtos = new ArrayList<>();
        for (Category category : categories) {
            getProductListFromACategory(category, productListResponseDtos);
        }
        return productListResponseDtos;
    }
    public AllCategoriesResponseDto getAllCategories() throws NotFoundException {
        // Get all unique categories
        List<Category> categories= categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new NotFoundException("No categories found");
        }
        AllCategoriesResponseDto allCategoriesResponseDto = new AllCategoriesResponseDto();
        List<String> categoryNames = new ArrayList<>();
        for(Category category: categories){
            categoryNames.add(category.getName());
        }
        allCategoriesResponseDto.setCategories(categoryNames);
        return allCategoriesResponseDto;
    }
}
