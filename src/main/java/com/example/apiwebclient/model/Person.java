package com.example.apiwebclient.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

    private String name;
    private int age;

    //generate random person
    public Person() {
        this.name = generateName();
        this.age = generateAge();
    }

    //generate random person name
    public String generateName() {
        String[] names = {"John", "Mary", "Peter", "David", "Bob", "Alice", "Jack", "Tom", "Jane", "Kate"};
        return names[(int) (Math.random() * names.length)];
    }

    //generate random person age
    public int generateAge() {
        return (int) (Math.random() * 100);
    }
}
