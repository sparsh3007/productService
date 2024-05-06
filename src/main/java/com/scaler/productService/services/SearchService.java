package com.scaler.productService.services;

import com.scaler.productService.Repositories.ProductRepository;
import com.scaler.productService.dtos.GenericProductDto;
import com.scaler.productService.models.Product;
//import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SearchService {
    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Page<GenericProductDto> searchProducts(String query, int pageNumber, int pageSize) {

        Sort sort = Sort.by("title").ascending()
                .and(Sort.by("description").descending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);

//        Page<Product> productPage = productRepository.findAllByTitle(query,pageable);
        Page<Product> productPage = productRepository.findAllByTitleContaining(query,pageable);

        List<Product> products = productPage.getContent();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product : products) {
            genericProductDtos.add(mapProductToGenericProductDto(product));
        }
        Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(genericProductDtos,pageable,productPage.getTotalElements());
        return genericProductDtoPage;
    }
    public GenericProductDto mapProductToGenericProductDto(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
//        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setRating(product.getRating());
//        genericProductDto.setPrice(product.getPrice().getPrice());
//        genericProductDto.setId(Long.valueOf(product.getUuid().toString()));
        return genericProductDto;
    }
}
