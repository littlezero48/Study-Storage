package com.example.querydsl.service;

import com.example.querydsl.domain.Products;
import com.example.querydsl.dto.SearchCondition;
import com.example.querydsl.repository.QuerydslRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final QuerydslRepository querydslRepository;

    public ProductService(QuerydslRepository querydslRepository) {
        this.querydslRepository = querydslRepository;
    }

    public List<Products> getSearchProduct(SearchCondition searchCondition) {
        return querydslRepository.searchProduct(searchCondition);
    }
}
