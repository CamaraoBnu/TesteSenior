package com.senior.repositories;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.senior.entities.Cart;
import com.senior.entities.Product;
import com.senior.entities.QCart;
import com.senior.entities.QProduct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private EntityManager entityManager;

    public Product getById(String id) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QProduct product = QProduct.product;
        return queryFactory
                .selectFrom(product)
                .where(product.id.eq(id))
                .fetchOne();
    }

    public List<Product> getAll() {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QProduct product = QProduct.product;
        return queryFactory
                .selectFrom(product)
                .fetch();
    }

    @Transactional
    public Product saveWithTransaction(Product product) {
        this.entityManager.persist(product);
        this.entityManager.merge(product);
        return product;
    }

    public Product save(Product product) {
        this.entityManager.persist(product);
        this.entityManager.merge(product);
        return product;
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public Product updateCart(Product product, String id) {
        QProduct qProduct = QProduct.product;
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        queryFactory.update(qProduct)
                .set(qProduct.name, product.getName())
                .set(qProduct.type, product.getType())
                .set(qProduct.active, product.isActive())
                .set(qProduct.price, product.getPrice())
                .where(qProduct.id.eq(id))
                .execute();

        return product;
    }

    @Transactional
    public Product deleteCartById(Product product, String id) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;
        queryFactory.delete(qProduct)
                .where(qProduct.id.eq(id))
                .execute();
        return product;
    }
}
