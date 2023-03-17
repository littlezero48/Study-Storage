package com.example.querydsl.repository.ImplExtend;

import com.example.querydsl.domain.Products;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;

import java.util.List;

import static com.example.querydsl.domain.QProducts.products;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Products> findByProductName(String productName) {
        return queryFactory.select(products)
                .from(products)
                .where(
                        products.productName.contains(productName)
                )
                .fetch();
    }
}