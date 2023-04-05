package com.example.querydsl.repository.extendSupport;


import com.example.querydsl.domain.Products;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.querydsl.domain.QProducts.products;


@Repository
public class ProductRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    public ProductRepositorySupport(JPAQueryFactory queryFactory) {
        super(Products.class);
        this.queryFactory = queryFactory;
    }

    public List<Products> findByProductId (String productName) {
        return queryFactory.select(products)
                .from(products)
                .where(
                        products.productName.contains(productName)
                )
                .fetch();
    }
}
