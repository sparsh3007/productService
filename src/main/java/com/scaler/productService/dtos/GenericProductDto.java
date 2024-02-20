package com.scaler.productService.dtos;

import com.scaler.productService.models.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    public Long id;
    private String  category;
    private String title;
    private double price;
    private String description;
    private String image;
    private Rating rating;
}
