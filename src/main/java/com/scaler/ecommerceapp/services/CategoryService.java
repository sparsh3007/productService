package com.scaler.ecommerceapp.services;

import com.scaler.ecommerceapp.Repositories.CategoryRepository;
import com.scaler.ecommerceapp.models.Category;
import com.scaler.ecommerceapp.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public void getProductsList(List<String> categoryUuids) {
        // Convert categoryUuids to UUID
        List<UUID> uuids = categoryUuids.stream().map(UUID::fromString).toList();

        List<Category> categories = categoryRepository.findAllById(uuids);

        for (Category category : categories) {
            for(Product product: category.getProducts()){
                System.out.println(product.getTitle());
            }
        }
    }
}
