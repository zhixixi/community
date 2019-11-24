package com.huvenbbs.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    /**
     *  defaultValue = "word" 表示当没有参数传过来的时候 name就会被设定默认值 "word"
     *  required=false 传入的参数不是必须的
     */
    @GetMapping("/")
    public String hello(){
        return "index";
    }
}
