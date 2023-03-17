package com.example.querydsl.repository;

import com.example.querydsl.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
