package com.example.lazyaddressdsl;

import com.example.lazyaddressdsl.entity.Address;
import com.example.lazyaddressdsl.entity.QAddress;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QueryDslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    @Test
    public void startQueryDsl() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QAddress m = new QAddress("m");

        Address friend1 = queryFactory
                .select(m)
                .from(m)
                .where(m.name.eq("친구1"))
                .fetchOne();

        assertThat(friend1.getName()).isEqualTo("친구1");
    }
}
