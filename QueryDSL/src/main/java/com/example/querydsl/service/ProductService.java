package com.example.querydsl.service;

import com.example.querydsl.domain.Products;
import com.example.querydsl.dto.SearchCondition;
import com.example.querydsl.repository.ProductRepository;
import com.example.querydsl.repository.extendSupport.ProductRepositorySupport;
import com.example.querydsl.repository.noImplnoExtend.QuerydslRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final QuerydslRepository querydslRepository;
//    private final ProductRepository productRepository;
    private final ProductRepositorySupport productRepositorySupport;

    public List<Products> getSearchProduct(SearchCondition searchCondition) {
        return querydslRepository.searchProduct(searchCondition);
    }

    public Page<Products> searchProductPagable(SearchCondition searchCondition, Pageable pageable) {
        return querydslRepository.searchProductPagable(searchCondition, pageable);
    }

    public List<Products> searchProductBysupport(String productName) {
        return productRepositorySupport.findByProductId(productName);
    }

}
