package com.scaler.productService.Repositories;

import com.scaler.productService.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query(value = "SELECT DISTINCT c.name FROM category c",nativeQuery = true)
    List<String> findAllUniqueCategories();
    Optional<Category> findByName(String name);
}
