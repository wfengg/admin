package com.demo.demo01.service;

import com.demo.demo01.bean.User;
import com.demo.demo01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public Integer findTUser(User user){
        return userMapper.selectByUsername(user);
    }


    public  int insert(User user){
        return userMapper.insertSelective(user);
    }
    public boolean exist(String username){
        int i = userMapper.selectByName(username);
        return i>0?true:false;
    }
}

