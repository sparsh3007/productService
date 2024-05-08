package com.scaler.productService.dtos;

import com.scaler.productService.models.Price;
import com.scaler.productService.models.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    public String id;
    private String category;
    private String title;
    private Price price;
    private String description;
    private String image;
    private int inventoryCount;
}
