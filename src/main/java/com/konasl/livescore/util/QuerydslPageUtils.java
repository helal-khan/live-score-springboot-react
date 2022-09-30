package com.konasl.livescore.util;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;

import java.util.Collections;
import java.util.List;

public class QuerydslPageUtils {

    public static <T> Page<T> createPage(Querydsl querydsl, JPQLQuery<T> query, Pageable pageable) {
        long total = query.fetchCount();
        JPQLQuery<T> pageableQuery = querydsl.applyPagination(pageable, query);
        List<T> content = total > pageable.getOffset() ? pageableQuery.fetch() : Collections.emptyList();
        return new PageImpl<>(content, pageable, total);
    }
}
