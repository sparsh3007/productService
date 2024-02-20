package com.scaler.productService.Repositories;

import com.scaler.productService.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitleAndPrice_Currency(String title, String currency);
    @Query(value = "SELECT * from product where title= :title",nativeQuery = true)
    List<Product> findByTitle(String title);

    // Write a query to find the product by title using a non-native query
//    @Query(value = "FROM Product WHERE Product.title= :title",nativeQuery = false)
//    Product findByTitle2(String title);

}
