package com.huvenbbs.community.controller;


import com.huvenbbs.community.pojo.User;
import com.huvenbbs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    /**
     *  defaultValue = "word" 表示当没有参数传过来的时候 name就会被设定默认值 "word"
     *  required=false 传入的参数不是必须的
     */
    @GetMapping("/")
    public String hello(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = userService.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
