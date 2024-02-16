package com.senior.dto.request;

import com.senior.entities.Cart;
import com.senior.entities.Product;
import com.senior.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {


    private String id;

    private String name;

    private double price;

    private ProductType type;

    private boolean active;

    public Product toEntity() {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .type(this.type)
                .active(this.active)
                .build();
    }
}
