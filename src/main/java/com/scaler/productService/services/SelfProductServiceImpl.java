package com.scaler.productService.services;

import com.scaler.productService.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        System.out.println("Calling from selfStoreProductService");
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id,GenericProductDto product) {
        return null;
    }
}
