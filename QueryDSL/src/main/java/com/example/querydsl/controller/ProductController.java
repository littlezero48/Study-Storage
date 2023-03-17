package com.example.querydsl.controller;

import com.example.querydsl.domain.Products;
import com.example.querydsl.dto.SearchCondition;
import com.example.querydsl.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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


}
