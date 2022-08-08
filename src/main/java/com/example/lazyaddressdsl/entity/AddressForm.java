package com.example.lazyaddressdsl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class AddressForm {
    private Long id;

    @NotEmpty(message = "이름은 필수 입니다.")
    @Pattern(regexp = "^[가-힣]+$", message = "친구 이름은 한글만 입력해주세요.")
    private String name;

    @NotNull(message = "나이는 필수 입니다.")
    @Range(min = 1, max = 20, message = "나이는 최대 20살까지 입력해주세요.")
    private int age;

    @NotEmpty(message = "전화번호는 필수 입니다.")
    @Size(min=10, max = 11, message = "전화번호는 최소 10자리 최대 11자리입니다.")
    private String phone;

    public AddressForm(Long id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}
