package com.scaler.productService.services;

import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(String id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws NotFoundException;
    public List<GenericProductDto> getAllProducts();
    public GenericProductDto deleteProduct(String id) throws NotFoundException;
    public GenericProductDto updateProductById(String id, GenericProductDto product) throws NotFoundException;
}
