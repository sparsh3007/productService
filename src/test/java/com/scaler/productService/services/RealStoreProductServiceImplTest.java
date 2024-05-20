package com.scaler.productService.services;

import com.scaler.productService.Repositories.ProductRepository;
import com.scaler.productService.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RealStoreProductServiceImplTest {
    @Autowired
    RealStoreProductServiceImpl realStoreProductService;
    @MockBean
    ProductRepository productRepository;
    @Test
//    @ParameterizedTest(name = "Test getProductById with id: {0}", value = "a103c20e-4bd5-46d3-a7d8-6535401ac56f")
    public void test_getProductById_when_product_not_found() {
        when(productRepository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class,()->realStoreProductService.getProductById("a103c20e-4bd5-46d3-a7d8-6535401ac56f"),"Product with uuid: a103c20e-4bd5-46d3-a7d8-6535401ac56f not found.");
    }
}
