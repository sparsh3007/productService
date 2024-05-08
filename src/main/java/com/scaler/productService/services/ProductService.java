package com.scaler.productService.services;

import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.dtos.GenericProductResponseDto;
import com.scaler.productService.dtos.UpdateProductRequestDto;
import com.scaler.productService.dtos.UpdateProductResponseDto;
import com.scaler.productService.exceptions.NotFoundException;
import com.scaler.productService.thirdpartyclients.fakestore.dtos.FakeStoreProductDto;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(String id) throws NotFoundException;
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws NotFoundException;
    public List<GenericProductResponseDto> getAllProducts() throws NotFoundException;
    public GenericProductDto deleteProduct(String id) throws NotFoundException;
    public UpdateProductResponseDto updateProductById(String id, UpdateProductRequestDto updateProductRequestDto) throws NotFoundException;
}
