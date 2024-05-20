package com.scaler.productService.security;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtData {
    private String email;
    private List<Role> roles; // Can be a list of role classes
}
