package com.huvenbbs.community.controller;

import com.huvenbbs.community.dto.AccessTokenDto;
import com.huvenbbs.community.dto.GithubUser;
import com.huvenbbs.community.dao.UserMapper;
import com.huvenbbs.community.pojo.User;
import com.huvenbbs.community.provider.GithubProvider;
import com.huvenbbs.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 授权
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.uri}")
    private String clientUri;

    @Value("${github.redirect.secret}")
    private String clientSecret;

    @Autowired
    private UserService userService;
    /**
     * 接受我们的参数 code , state
     * @param code
     * @param state
     * @return
     */
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest httpServletRequest) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(clientUri);
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        // 拿到 accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        //通过 accessToken 可以在 Github中拿到 User信息
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        System.out.println("认证的Github用户名为:" + githubUser.getName());
        if (githubUser != null) {
            System.out.println(githubUser);
            // 登录成功,将对象放进 session
            httpServletRequest.getSession().setAttribute("user",githubUser);
            //将数据插入到数据库中
            User user = new User();
            // 一般使用UUID 来做token
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            // GitHub的id是Long,User的AccountId是 String 类型的, 类型转换
            user.setAccountId(String.valueOf(githubUser.getId()));
            // 将当前系统 Time 的毫秒值写进 gmtCreate
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            // 插入数据库
            userService.insert(user);
            return "redirect:/";
        }else {
            // 登录失败
            System.out.println("空");
            return "redirect:/";
        }
//        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        // 删除session
        session.removeAttribute("user");
        return "redirect:/";
    }
}
