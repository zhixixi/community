package com.huvenbbs.community.provider;

import com.alibaba.fastjson.JSON;
import com.huvenbbs.community.dto.AccessTokenDto;
import com.huvenbbs.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Component 注解: 将当前的类初始化到 Spring 容器的上下文
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        // 使用 okhttp 去发送一个post请求, 传送 access_token
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //返回一个字符串
            // access_token=b97a46cb6bffea9d695b49a4a6dc847833a1c30c&scope=user&token_type=bearer
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从Github上拿到 User信息
     *
     * @param accessToken
     * @return
     */
    public GithubUser getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //拿到 Github 返回过来的用户数据,是字符串(json格式的)
            String string = response.body().string();
            //通过 fastjson 帮我们自动解析成 GithubUser对象
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
