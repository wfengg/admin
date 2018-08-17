package com.demo.demo01.mapper;

import com.demo.demo01.bean.Replymsg;
import com.demo.demo01.bean.ReplymsgExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReplymsgMapper {
    int countByExample(ReplymsgExample example);

    int deleteByExample(ReplymsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Replymsg record);

    int insertSelective(Replymsg record);

    List<Replymsg> selectByExample(ReplymsgExample example);

    Replymsg selectByPrimaryKey(Integer id);

    List<Replymsg> selectByUserId(Integer id);

    int updateByExampleSelective(@Param("record") Replymsg record, @Param("example") ReplymsgExample example);

    int updateByExample(@Param("record") Replymsg record, @Param("example") ReplymsgExample example);

    int updateByPrimaryKeySelective(Replymsg record);

    int updateByPrimaryKey(Replymsg record);
}