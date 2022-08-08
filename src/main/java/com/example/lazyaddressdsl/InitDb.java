package com.example.lazyaddressdsl;

import com.example.lazyaddressdsl.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Address address = createAddress("친구김",1,"0277445588");
            em.persist(address);
            Address address2 = createAddress("친구이",12,"01012345678");
            em.persist(address2);
            Address address3 = createAddress("친구박",13,"01085196220");
            em.persist(address3);
        }

        private Address createAddress(String name, Integer age, String phone) {
            Address address = new Address();
            address.setName(name);
            address.setAge(age);
            address.setPhone(phone);
            return address;
        }
    }
}
