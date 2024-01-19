package com.scaler.ecommerceapp.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rating extends BaseModel{
    double rate;
    int count;
}
