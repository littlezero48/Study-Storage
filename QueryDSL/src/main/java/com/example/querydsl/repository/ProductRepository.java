package com.example.querydsl.repository;

import com.example.querydsl.domain.Products;
import com.example.querydsl.repository.ImplExtend.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long>, ProductRepositoryCustom {
}
