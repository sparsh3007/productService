package com.scaler.ecommerceapp.thirdpartyclients.fakestore.dtos;

import com.scaler.ecommerceapp.models.Rating;
import lombok.Getter;
import lombok.Setter;

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
