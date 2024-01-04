package com.example.apiwebclient.controller;

import com.example.apiwebclient.model.Person;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiControllerTest {

    @Autowired
    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;


    @Test
    void getPersonsTestRestTemplate() {

        long startTime = System.currentTimeMillis();

        String url = "http://localhost:8080/api/persons"; // Replace with your actual server URL

        Person[] persons = restTemplate.getForObject(url, Person[].class);

        assertNotNull(persons, "Persons should not be null");
        assertTrue(persons.length > 0, "Persons list should not be empty");

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time with Rest Template: " + (endTime - startTime) + " milliseconds");
    }

    @Test
    void getWebClientBlocking() {

        long startTime = System.currentTimeMillis();

        String url = "http://localhost:8080/api/persons"; // Replace with your actual server URL

        Person[] persons = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Person[].class)
                .block();

        assertNotNull(persons, "Persons should not be null");
        assertTrue(persons.length > 0, "Persons list should not be empty");

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time with WebClient Blocking: " + (endTime - startTime) + " milliseconds");
    }

    @Test
    void getPersonsTestRestTemplateBlocking() {

        long startTime = System.currentTimeMillis();

        String url = "http://localhost:8080/api/persons"; // Replace with your actual server URL

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person[] persons = restTemplate.getForObject(url, Person[].class);
            assertNotNull(persons, "Persons should not be null");
            assertTrue(persons.length > 0, "Persons list should not be empty");
            personList.addAll(List.of(persons));
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time with Rest Template: " + (endTime - startTime) + " milliseconds");
    }

    @Test
    void getWebClientNonBlocking() {

        long startTime = System.currentTimeMillis();

        String url = "http://localhost:8080/api/persons"; // Replace with your actual server URL

        Mono<Person[]> personMono = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Person[].class);

        List<Person> personList = new ArrayList<>();
        Flux.range(0, 100)
                .flatMap(i -> personMono)
                .doOnNext(persons -> {
                    personList.addAll(List.of(persons));
                    assertNotNull(persons, "Persons should not be null");
                    assertTrue(persons.length > 0, "Persons list should not be empty");
                })
                .blockLast();

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time with WebClient Non Blocking: " + (endTime - startTime) + " milliseconds");
    }
}