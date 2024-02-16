package com.senior.entities;

import java.util.Date;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data()
@EqualsAndHashCode(callSuper = false)
@Entity(name = "cart_product")
@Table(name = "cart_product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "qt_products", nullable = false, unique = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id",  unique = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", unique = false)
    private Cart cart;

    @CreatedDate
    @Column(name = "created_at", nullable = false, unique = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = true, unique = false)
    private Date updatedAt;

    @Column(name = "deleted_at", nullable = true, unique = false)
    private Date deletedAt;
}
