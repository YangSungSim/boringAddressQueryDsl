package com.example.lazyaddressdsl.repository;

import com.example.lazyaddressdsl.entity.Address;
import static com.example.lazyaddressdsl.entity.QAddress.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AddressRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    public AddressRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Address findOne(Long id) {
        return queryFactory
                .select(address)
                .from(address)
                .where(address.id.eq(id))
                .fetchOne();
    }

    public List<Address> findAddress() {
        return queryFactory
                .selectFrom(address)
                .fetch();
    }

    public Address findByName(String name) {
        return queryFactory
                .selectFrom(address)
                .where(address.name.eq(name))
                .fetchOne();
    }

    @Transactional
    public void save(Address address) {
       em.persist(address);
       em.flush();
       em.close();
    }
    @Transactional
    public void deleteAddress(Long addressId) {
        queryFactory
                .delete(address)
                .where(address.id.eq(addressId))
                .execute();
    }

    @Transactional
    public void update(Address addressDto) {
        queryFactory
                .update(address)
                .where(address.id.eq(addressDto.getId()))
                .set(address.name, addressDto.getName())
                .set(address.age, addressDto.getAge())
                .set(address.phone, addressDto.getPhone())
                .execute();
    }

}
