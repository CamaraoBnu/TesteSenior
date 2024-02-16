package com.senior.dto.response;

import com.senior.entities.Cart;
import com.senior.entities.Product;
import com.senior.enums.ProductType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String id;

    private String name;

    private double price;

    private ProductType type;

    private boolean active;

    private Date createdAt;

    private Date updatedAt;


    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.type = product.getType();
        this.active = product.isActive();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();

    }


    public static ProductResponse fromEntity(Product product) {
        ProductResponse response = ProductResponse
                .builder()
                .name(product.getName())
                .price(product.getPrice())
                .type(product.getType())
                .active(product.isActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();


        return response;


    }
}
