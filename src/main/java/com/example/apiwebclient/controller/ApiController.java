package com.example.apiwebclient.controller;

import com.example.apiwebclient.model.Person;
import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ApiController {

    private final List<Person> persons = new ArrayList<>();

    @PostConstruct
    private void init() {
        for (int i = 0; i < 100; i++) {
            persons.add(new Person());
        }
    }

    @GetMapping("/api/persons")
    public List<Person> getPersons() throws InterruptedException {
        //3초 지연시키기
        Thread.sleep(3000);
        return persons;
    }

}
