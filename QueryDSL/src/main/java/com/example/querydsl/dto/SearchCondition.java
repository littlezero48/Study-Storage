package com.example.querydsl.dto;

import com.querydsl.core.types.Order;
import lombok.Getter;

@Getter
public class SearchCondition {

    private String keyword;
    private String bigCategory;
    private String smallCategory;
    private int priceMin;
    private int priceMax;
    private String orderByPoint;
    private Order direction;
}
