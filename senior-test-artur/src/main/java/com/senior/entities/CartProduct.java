package com.senior.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @Positive
    @Column(name = "qt_products", nullable = false)
    private int quantity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
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
