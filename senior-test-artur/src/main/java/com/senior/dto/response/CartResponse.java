package com.senior.dto.response;

import com.senior.entities.Cart;
import com.senior.enums.CartStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private String id;

    private double discount;

    private CartStatus status;

    private Date createdAt;

    private Date updatedAt;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.discount = cart.getDiscount();
        this.status = cart.getStatus();
        this.createdAt = cart.getCreatedAt();
        this.updatedAt = cart.getUpdatedAt();
    }

    public static CartResponse fromEntity(Cart cart) {
        CartResponse response = CartResponse
                .builder()
                .id(cart.getId())
                .discount(cart.getDiscount())
                .status(cart.getStatus())
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();

        return response;
    }
}
