package com.scaler.productService.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    /* For Hibernate prior to v6.2
    @GeneratedValue(generator ="uuidgenerator")
    @GenericGenerator(name = "uuidgenerator",  type =  org.hibernate.id.uuid.UuidGenerator.class)
     */
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id",columnDefinition = "binary(16)",updatable = false, nullable = false)
    public UUID uuid;
}