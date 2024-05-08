package com.scaler.productService.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllCategoriesResponseDto {
    List<String> categories;
}
