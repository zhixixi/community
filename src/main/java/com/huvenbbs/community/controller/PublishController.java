package com.huvenbbs.community.controller;

import com.huvenbbs.community.pojo.Question;
import com.huvenbbs.community.pojo.User;
import com.huvenbbs.community.service.QuestionService;
import com.huvenbbs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by v20 Luna on 2019/11/27
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    /**
     * Get 显示页面
     *
     * @return
     */
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /**
     * Post 处理请求
     *
     * @return
     */
    @PostMapping("/publish")
    public String doPublish(HttpServletRequest request,
                            Question question,
                            Model m) {

//        Cookie[] cookies = request.getCookies();
//        User user = null;
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("token")) {
//                String token = cookie.getValue();
//                user = userService.findByToken(token);
//                if (user != null) {
//                    request.getSession().setAttribute("user", user);
//                }
//                break;
//            }
//        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            m.addAttribute("error", "你还未登录");
            return "publish";
        } else {
            //将数据写入进question
            question.setCreatorId(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionService.insert(question);
            m.addAttribute("success","发布成功");
        }

        return "publish";
    }
}
