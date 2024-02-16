package com.senior.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
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

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProducts;

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
}
