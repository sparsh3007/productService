package com.scaler.productService.Repositories;

import com.scaler.productService.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRespository extends JpaRepository<Price, UUID> {
}
