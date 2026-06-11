package com.booknexa.backend.dto;

import com.booknexa.backend.model.Role;

import java.math.BigDecimal;

public class AuthResponse {

    private String message;
    private String token;
    private Long userId;
    private String name;
    private String email;
    private Role role;
    private boolean premium;
    private BigDecimal discountRate;

    public AuthResponse(String message, String token, Long userId, String name, String email,
                        Role role, boolean premium, BigDecimal discountRate) {
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.premium = premium;
        this.discountRate = discountRate;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public boolean isPremium() {
        return premium;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }
}