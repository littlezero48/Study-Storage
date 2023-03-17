package com.example.querydsl.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfig {

    @PersistenceContext
    // @PersistenceContext로 주입받은 Entity Manager은 Proxy로 감싸진다.
    private EntityManager entityManager;

    @Bean
    // JPAQuery를 생성해주는 factory클래스
    // QueryDSL이 query를 생성할 수 있도록 EntityManager를 주입한다.
    // JPAQueryFactory를 Bean으로 등록시 동시성 문제에 대해 생각해 볼 수 있으나 위의 Proxy를 통해 생성한 Entity Manager라 Thread-safe를 보장
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}

