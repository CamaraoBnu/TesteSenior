package com.senior.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.senior.enums.CartStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "cart")
@Table(name = "cart")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cart {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @OneToMany(mappedBy = "id")
    private List<CartProduct> cartProduct = new ArrayList<>();

    @PositiveOrZero
    @Max(value = 100, message = "Discount must be between 0.0 and 100.0")
    @Column(name = "amt_discount", nullable = true, unique = false)
    private double discount;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_status", nullable = false, unique = false)
    private CartStatus status;

    @CreatedDate
    @Column(name = "created_at", nullable = false, unique = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true, unique = false)
    private Date updatedAt;

    @Column(name = "deleted_at", nullable = true, unique = false)
    private Date deletedAt;

    public Cart() {
        this.discount = 0d;
        this.status = CartStatus.OPEN;
    }

    @Builder
    public Cart(double discount, CartStatus status) {
        this.discount = discount ;
        if (status != null) {
            this.status = status;
        } else {
            this.status = CartStatus.OPEN;
        }
    }

}
