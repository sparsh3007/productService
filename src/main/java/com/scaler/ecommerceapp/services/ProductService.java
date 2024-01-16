package com.scaler.ecommerceapp.services;

import com.scaler.ecommerceapp.dtos.GenericProductDto;
import com.scaler.ecommerceapp.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws NotFoundException;
    public List<GenericProductDto> getAllProducts();
    public GenericProductDto deleteProduct(Long id) throws NotFoundException;
    public GenericProductDto updateProductById(Long id, GenericProductDto product) throws NotFoundException;
}
