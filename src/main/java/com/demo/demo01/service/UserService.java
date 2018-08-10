package com.demo.demo01.service;

import com.demo.demo01.bean.User;
import com.demo.demo01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public Integer selectByUser(User user){
        return userMapper.selectByUser(user);
    }

}

