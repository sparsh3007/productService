package com.scaler.productService.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListResponseDto {
    private String productId;
    private String categoryId;
    private String category;
    private String title;
    private double price;
    private String image;
    private double rating;
}
