package com.csci5409.lmsbootapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
public class HelloController {

    @GetMapping("/test")
    public String index() {
        return "Greetings from Library Management Boot Application! ";
    }

}
