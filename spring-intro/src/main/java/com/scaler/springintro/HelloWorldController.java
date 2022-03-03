package com.scaler.springintro;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.function.Function;

@RestController
public class HelloWorldController {

    String x = "asfasfa";

    @GetMapping("/hello")
    String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello/greet")
    String helloGreet() {
        return "Hello my Dear!";
    }

    @GetMapping("/xyz")
    String omg() {
        return "Oh My God!";
    }
}
