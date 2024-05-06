package com.scaler.productService.thirdpartyclients.fakestore.dtos;

import com.scaler.productService.models.Rating;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FakeStoreProductDto {
    public Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
}
