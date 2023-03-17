package com.example.querydsl.controller;

import com.example.querydsl.domain.Products;
import com.example.querydsl.dto.SearchCondition;
import com.example.querydsl.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<Products> searchProduct(@RequestBody SearchCondition searchCondition) {
        return productService.getSearchProduct(searchCondition);
    }

    @GetMapping("/productPageable")
    public Page<Products> searchProduct(@RequestBody SearchCondition searchCondition, Pageable pageable) {
        return productService.searchProductPagable(searchCondition, pageable);
    }

    @GetMapping("/productSupport/{productName}")
    public List<Products> searchProductSupport(@PathVariable String productName) {
        return productService.searchProductBysupport(productName);
    }

    @GetMapping("/productImpl/{productName}")
    public List<Products> searchProductImpl(@PathVariable String productName) {
        return productService.searchProductByImpl(productName);
    }
}
