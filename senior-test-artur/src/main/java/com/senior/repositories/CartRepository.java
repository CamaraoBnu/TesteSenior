package com.senior.repositories;

import java.util.List;

import com.querydsl.jpa.impl.JPAUpdateClause;
import com.senior.enums.CartStatus;
import com.senior.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.senior.entities.Cart;
import com.senior.entities.QCart;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class CartRepository {

    @Autowired
    private EntityManager entityManager;

    public Cart getById(String id) {
        log.debug("Querying by ID %s", id);

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCart cart = QCart.cart;
        return queryFactory
                .selectFrom(cart)
                .where(cart.id.eq(id))
                .fetchOne();
    }

    public List<Cart> getAll() {
        log.debug("Querying for all carts.");

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCart cart = QCart.cart;
        return queryFactory
                .selectFrom(cart)
                .fetch();
    }

    public List<Cart> getCartsByStatus(CartStatus status) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCart cart = QCart.cart;
            return queryFactory
                    .selectFrom(cart)
                    .where(cart.status.eq(status))
                    .fetch();

    }

    @Transactional
    public Cart saveWithTransaction(Cart cart) {
        this.entityManager.persist(cart);
        this.entityManager.merge(cart);
        return cart;
    }

    public Cart save(Cart cart) {
        this.entityManager.persist(cart);
        this.entityManager.merge(cart);
        return cart;
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public Cart updateCart(Cart cart, String id) {
        QCart qCart = QCart.cart;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        queryFactory.update(qCart)
                .set(qCart.status, cart.getStatus())
                .set(qCart.discount, cart.getDiscount())
                .where(qCart.id.eq(id))
                .execute();

        return cart;
    }

    @Transactional
    public Cart deleteCartById(Cart cart, String id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCart qCart = QCart.cart;
        queryFactory.delete(qCart)
                .where(qCart.id.eq(id))
                .execute();
        return cart;
    }
}
