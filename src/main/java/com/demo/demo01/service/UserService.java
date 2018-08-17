package com.demo.demo01.service;

import com.demo.demo01.bean.User;
import com.demo.demo01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;


    public Integer selectByUser(User user){
        return userMapper.selectByUser(user);
    }

    public  User selectById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
    public  boolean exist(String username){
        return userMapper.selectByUsername(username)==0;
    }
    public  void insert(User user){
        userMapper.insertSelective(user);
    }
}

