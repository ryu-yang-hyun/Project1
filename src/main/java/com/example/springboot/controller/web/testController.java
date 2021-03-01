package com.example.springboot.controller.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class testController {

    private static final Logger log =  LoggerFactory.getLogger(testController.class);

    @GetMapping("/")
    public String test() {

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        return "test";
    }
}
