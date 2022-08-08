package com.example.lazyaddressdsl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"name","age","phone"})
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    private String phone;

    public Address(Long id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Address(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
