package com.senior.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.senior.entities.Cart;
import com.senior.enums.CartStatus;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    private String id;

    private double discount = 0d;

    private CartStatus status;

    public Cart toEntity() {
        return Cart.builder()
                .status(this.status)
                .discount(this.discount)
                .build();
    }
}
