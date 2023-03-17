package com.example.querydsl.repository;

import com.example.querydsl.domain.Products;
import com.example.querydsl.dto.SearchCondition;
import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.querydsl.domain.QProducts.products;

@Repository
public class QuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public QuerydslRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Products> searchProduct (SearchCondition searchCondition) {
        return jpaQueryFactory
                .selectFrom(products)
                .where(
                        productNameContain(searchCondition.getKeyword()),
                        bigCategoryEq(searchCondition.getBigCategory()),
                        smallCategoryEq(searchCondition.getSmallCategory()),
                        priceBw(searchCondition.getPriceMin(), searchCondition.getPriceMax())
                )
                .orderBy(orderProductBy(searchCondition.getOrderByPoint(), searchCondition.getDirection()))
                .fetch();
    }

    public Page<Products> searchProductPagable (SearchCondition searchCondition, Pageable pageable) {
        List<Products> productsList = jpaQueryFactory
                .selectFrom(products)
                .where(
                        productNameContain(searchCondition.getKeyword()),
                        bigCategoryEq(searchCondition.getBigCategory()),
                        smallCategoryEq(searchCondition.getSmallCategory()),
                        priceBw(searchCondition.getPriceMin(), searchCondition.getPriceMax())
                )
                .orderBy(orderProductBy(searchCondition.getOrderByPoint(), searchCondition.getDirection()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = jpaQueryFactory
                .select(products.id.count())
                .from(products)
                .where(
                        productNameContain(searchCondition.getKeyword()),
                        bigCategoryEq(searchCondition.getBigCategory()),
                        smallCategoryEq(searchCondition.getSmallCategory()),
                        priceBw(searchCondition.getPriceMin(), searchCondition.getPriceMax())
                )
                .fetchOne();

        return new PageImpl<>(productsList, pageable, count);
    }

    private OrderSpecifier<?> orderProductBy(String orderByPoint, Order direction) {

        switch(orderByPoint != null ? orderByPoint : "null") {
            case "productName" :
                return new OrderSpecifier<>(direction, products.productName);
            case "price" :
                return new OrderSpecifier<>(direction, products.price);
            case "null" :
            default:
                return new OrderSpecifier<>(direction, products.id);
        }
    }

    private BooleanExpression productNameContain(String productName) {
        return productName != null ? products.productName.contains(productName) : null;
    }

    private BooleanExpression bigCategoryEq(String bigCategory) {
        return bigCategory != null ? products.bigCategory.eq(bigCategory) : null;
    }

    private BooleanExpression smallCategoryEq(String smallCategory) {
        return smallCategory != null ? products.bigCategory.eq(smallCategory) : null;
    }

    private BooleanExpression priceBw(Integer priceMin, Integer priceMax) {
        if (priceMin == null && priceMax == null) {
            return null;
        }
        return products.price.between(priceMin, priceMax);
    }
}
