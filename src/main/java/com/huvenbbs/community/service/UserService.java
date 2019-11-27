package com.huvenbbs.community.service;

import com.huvenbbs.community.dao.UserMapper;
import com.huvenbbs.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserMapper {

@Autowired
private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
    @Override
    public User findByToken(String token) {
       return userMapper.findByToken(token);
    }
}
