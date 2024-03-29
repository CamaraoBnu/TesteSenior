package com.senior.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.senior.enums.ProductType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "product")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Builder
    public Product(String name, double price, ProductType type, boolean active) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.active = active;
        this.createdAt = this.getCreatedAt();
        this.updatedAt = this.getUpdatedAt();
        this.deletedAt = this.getDeletedAt();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<CartProduct> cartProduct = new ArrayList<>();

    @NotNull(message = "Product name can't be null")
    @Column(name = "ds_name", nullable = false, unique = false)
    private String name;

    @PositiveOrZero
    @Column(name = "amt_price", nullable = false, unique = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_type", nullable = false, unique = false)
    private ProductType type;

    @Column(name = "is_active", nullable = false, unique = false)
    private boolean active;

    @CreatedDate
    @Column(name = "created_at", nullable = false, unique = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true, unique = false)
    private Date updatedAt;

    @Column(name = "deleted_at", nullable = true, unique = false)
    private Date deletedAt;

    public boolean isProductProductType() {
        return this.type == ProductType.PRODUCT;
    }

    public boolean isProductServiceType() {
        return this.type == ProductType.SERVICE;
    }
}
