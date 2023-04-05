package com.example.querydsl.repository.ImplExtend;

import com.example.querydsl.domain.Products;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Products> findByProductName(String productName);
}
