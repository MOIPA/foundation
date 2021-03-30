package com.company.reflect;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {
    @EnableAuth
    private String name;
    private String age;
    private String fname;

    public Person(String name, String age, String fname) {
        super();
        this.name = name;
        this.age = age;
        this.fname = fname;
    }
}
