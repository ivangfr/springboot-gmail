package com.mycompany.springbootgmail.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/callback")
public class CallbackController {

    @GetMapping
    public String callbackCode(@RequestParam String code) {
        return code;
    }

}
