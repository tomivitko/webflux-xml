package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TestController {
    private final WebClient webClient;
    private final XmlMapper xmlMapper = new XmlMapper();

    @GetMapping("/test")
    public Object test() {
        return webClient.get()
                .uri("https://catfact.ninja/fact?max_length=90")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/webflux")
    public Object xml2() throws JsonProcessingException {
        return webClient.get()
                .uri("http://localhost:8080/api/xml")
                .accept(MediaType.TEXT_XML)
                .retrieve()
                .bodyToMono(Person.class)
                .block();
    }

    @GetMapping(value = "xml", produces = "text/xml")
    public Object xml() throws JsonProcessingException {
        Person person = new Person();
        person.setName("john");
        return xmlMapper.writeValueAsString(person);
    }
}
