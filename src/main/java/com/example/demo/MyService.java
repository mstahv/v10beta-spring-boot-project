package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MyService {
    public String sayHi() {
        return "Hello there. Server time is " + LocalDateTime.now();
    }
}
