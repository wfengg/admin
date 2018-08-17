package com.demo.demo01.service;

import com.demo.demo01.bean.Msgboard;
import com.demo.demo01.mapper.MsgboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MsgboardService {

    @Autowired
    @Qualifier("msgboardMapper")
    MsgboardMapper msgboardMapper;

    public int insertSelective(Msgboard msgboard){
        return msgboardMapper.insertSelective(msgboard);
    }

    public List<Msgboard> selectByUserId(int userid,int startIndex,int pageSize){
        return msgboardMapper.selectByUSerId(userid,startIndex,pageSize);
    }

    public  int getTotalRecord(int userid){
        return msgboardMapper.getTotalRecord(userid);
    }

    public int getTotalRecords(){return msgboardMapper.getTotalRecords();}

    public List<Msgboard> selectAll (int startIndex,int pageSize){
        return msgboardMapper.selectAll(startIndex,pageSize);
    }

    public Integer deleteById(Integer id){
        return msgboardMapper.deleteByPrimaryKey(id);
    }

    public Msgboard selectById(Integer id){return msgboardMapper.selectByPrimaryKey(id);}
}
