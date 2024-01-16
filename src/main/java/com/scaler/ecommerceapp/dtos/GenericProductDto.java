package com.scaler.ecommerceapp.dtos;

import com.scaler.ecommerceapp.models.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    public Long id;
    private String title;
    private String description;
    private String image;
    private String  category;
    private double price;
    private Rating rating;
}
