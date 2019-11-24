package com.huvenbbs.community.hellocontroller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    /**
     *  defaultValue = "word" 表示当没有参数传过来的时候 name就会被设定默认值 "word"
     *  required=false 传入的参数不是必须的
     */
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name")String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
