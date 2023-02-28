package com.example.webfluxdemo.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "Person")
public class Person {
    private String name;
}
