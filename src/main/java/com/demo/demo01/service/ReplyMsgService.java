package com.demo.demo01.service;

import com.demo.demo01.bean.Replymsg;
import com.demo.demo01.mapper.ReplymsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyMsgService {

    @Autowired
    @Qualifier("replymsgMapper")
    private ReplymsgMapper replymsgMapper;

    public Integer insertReply(Replymsg replymsg){
        return replymsgMapper.insertSelective(replymsg);
    }

    public List<Replymsg> selectByUserId(Integer id){return replymsgMapper.selectByUserId(id);}
}
