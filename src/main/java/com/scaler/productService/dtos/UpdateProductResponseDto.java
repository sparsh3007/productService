package com.scaler.productService.dtos;

import com.scaler.productService.models.Rating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductResponseDto extends GenericProductDto{
    private Rating rating;
}
