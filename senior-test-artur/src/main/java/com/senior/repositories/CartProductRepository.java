package com.senior.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.senior.entities.CartProduct;
import com.senior.entities.Product;
import com.senior.entities.QCartProduct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartProductRepository {

    @Autowired
    private EntityManager entityManager;

    public CartProduct getById(String id) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCartProduct cartProduct = QCartProduct.cartProduct;
        return queryFactory
                .selectFrom(cartProduct)
                .where(cartProduct.id.eq(id))
                .fetchOne();
    }

    public List<CartProduct> getAll() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCartProduct cartProduct = QCartProduct.cartProduct;
        return queryFactory
                .selectFrom(cartProduct)
                .fetch();
    }

    @Transactional
    public CartProduct saveWithTransaction(CartProduct cartProduct) {
        this.entityManager.persist(cartProduct);
        this.entityManager.merge(cartProduct);
        return cartProduct;
    }

    public CartProduct save(CartProduct cartProduct) {
        this.entityManager.persist(cartProduct);
        this.entityManager.merge(cartProduct);
        return cartProduct;
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public CartProduct updateCart(CartProduct cartProduct, String id) {
        QCartProduct qCartProduct = QCartProduct.cartProduct;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        queryFactory.update(qCartProduct)
                .set(qCartProduct.quantity, cartProduct.getQuantity())
                .set(qCartProduct.product, cartProduct.getProduct())
                .set(qCartProduct.cart,cartProduct.getCart())
                .where(qCartProduct.id.eq(id))
                .execute();

        return cartProduct;
    }

    @Transactional
    public CartProduct deleteCartById(CartProduct cartProduct, String id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCartProduct qCartProduct = QCartProduct.cartProduct;
        queryFactory.delete(qCartProduct)
                .where(qCartProduct.id.eq(id))
                .execute();
        return cartProduct;
    }
}
