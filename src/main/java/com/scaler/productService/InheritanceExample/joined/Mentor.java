package com.scaler.productService.InheritanceExample.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_mentor")
@PrimaryKeyJoinColumn(name = "mentor_id")
public class Mentor extends User {
    private double avgRating;
}
