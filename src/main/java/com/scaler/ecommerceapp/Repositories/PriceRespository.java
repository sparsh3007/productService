package com.scaler.ecommerceapp.Repositories;

import com.scaler.ecommerceapp.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRespository extends JpaRepository<Price, UUID> {
}
