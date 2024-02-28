package com.example.webtest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王鹏
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class Api {

    @RequestMapping("/hello")
    public String hello(){
        log.info("hello world");
        return "hello";
    }
}
