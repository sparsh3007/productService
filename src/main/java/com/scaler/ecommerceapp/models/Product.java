package com.scaler.ecommerceapp.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST} )
    private Price price;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Rating rating;
}
