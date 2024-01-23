package com.scaler.ecommerceapp.InheritanceExample.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_ta")
public class TA extends User {
    private double avgRating;
    private String startTime;
    private String endTime;
}
