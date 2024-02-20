package com.scaler.productService.InheritanceExample.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_ta")
@PrimaryKeyJoinColumn(name = "ta_id")
public class TA extends User {
    private double avgRating;
    private String startTime;
    private String endTime;
}
