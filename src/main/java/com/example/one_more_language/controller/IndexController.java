package com.example.one_more_language.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    //@Value('${myconfig}')
    //private String config;

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
