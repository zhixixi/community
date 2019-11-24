package com.huvenbbs.community.controller;

import com.huvenbbs.community.dto.AccessTokenDto;
import com.huvenbbs.community.dto.GithubUser;
import com.huvenbbs.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 授权
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    /**
     * 接受我们的参数 code , state
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDto.setState(state);
        accessTokenDto.setClient_id("d09a650263d08038e5be");
        accessTokenDto.setClient_secret("c3a1328d8302510c85ce2e0d12083f8a61710f73");
        // 拿到 accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        //通过 accessToken 可以在 Github中拿到 User信息
        GithubUser user = githubProvider.getGithubUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
