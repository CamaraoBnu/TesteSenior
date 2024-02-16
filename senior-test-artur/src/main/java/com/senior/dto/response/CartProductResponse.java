package com.senior.dto.response;

import com.senior.entities.Cart;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProductResponse {

    private String id;

    private int quantity;

    private Product product;

    private Cart cart;

    private Date createdAt;

    private Date updatedAt;

    public CartProductResponse(CartProduct cartProduct) {
        this.id = cartProduct.getId();
        this.quantity = cartProduct.getQuantity();
        this.product = cartProduct.getProduct();
        this.cart = cartProduct.getCart();
        this.createdAt = cartProduct.getCreatedAt();
        this.updatedAt = cartProduct.getUpdatedAt();
    }

    public static CartProductResponse fromEntity(CartProduct cartProduct) {
        CartProductResponse response = CartProductResponse
                .builder()
                .id(cartProduct.getId())
                .quantity(cartProduct.getQuantity())
                .product(cartProduct.getProduct())
                .cart(cartProduct.getCart())
                .createdAt(cartProduct.getCreatedAt())
                .updatedAt(cartProduct.getUpdatedAt())
                .build();

        return response;
    }
}
