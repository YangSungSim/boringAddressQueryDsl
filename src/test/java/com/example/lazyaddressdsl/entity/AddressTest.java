package com.example.lazyaddressdsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class AddressTest {

    @Autowired
    EntityManager em;

    @Test
    public void testAddress() {
        Address address = new Address("친구1",5,"01014788888");
        em.persist(address);

        em.flush();
        em.clear();

        List<Address> result = em.createQuery("select m from Address m", Address.class).getResultList();

        for (Address address1 : result) {
            System.out.println(address1.toString());
        }

    }
}