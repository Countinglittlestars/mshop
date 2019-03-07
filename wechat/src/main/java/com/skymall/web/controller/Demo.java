package com.skymall.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统一了接口名称
 * Wc***Controller
 * 接口地址 /mshop/we***1/***
 */

@RestController
@RequestMapping("/mshop/we***")

public class Demo {
    @RequestMapping(value = "/***", method = RequestMethod.GET)
    public String demo(){
        return "gogo";
    }
}



