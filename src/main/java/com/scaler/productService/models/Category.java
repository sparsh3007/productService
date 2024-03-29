package com.scaler.productService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    String name;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    List<Product> products;
}
