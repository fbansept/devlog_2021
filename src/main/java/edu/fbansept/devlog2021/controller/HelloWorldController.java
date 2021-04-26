package edu.fbansept.devlog2021.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping({"/hello","/coucou"})
    public String hello(){
        return "Hello world";
    }
}
