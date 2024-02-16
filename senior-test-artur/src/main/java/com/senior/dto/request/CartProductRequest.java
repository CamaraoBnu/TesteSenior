package com.senior.dto.request;

import com.senior.entities.Cart;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductRequest {

    private String id;

    private int quantity;

    private Product product;

    private Cart cart;


    public CartProduct toEntity(Product product, Cart cart) {
        return CartProduct.builder()
                .id(this.id)
                .quantity(this.quantity)
                .product(product)
                .cart(cart)
                .build();
    }

    public CartProduct toEntity() {
        return CartProduct.builder()
                .id(this.id)
                .quantity(this.quantity)
                .product(this.product)
                .cart(this.cart)
                .build();
    }
}