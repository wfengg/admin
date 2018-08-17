package com.demo.demo01.mapper;

import com.demo.demo01.bean.Msgboard;
import com.demo.demo01.bean.MsgboardExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MsgboardMapper {
    int countByExample(MsgboardExample example);

    int deleteByExample(MsgboardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Msgboard record);
    List<Msgboard> selectByUSerId(@Param("userid") int userid,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
    List<Msgboard> selectAll(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
    int insertSelective(Msgboard record);
    int getTotalRecord(int userid);
    List<Msgboard> selectByExample(MsgboardExample example);
    int getTotalRecords();
    Msgboard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Msgboard record, @Param("example") MsgboardExample example);

    int updateByExample(@Param("record") Msgboard record, @Param("example") MsgboardExample example);

    int updateByPrimaryKeySelective(Msgboard record);

    int updateByPrimaryKey(Msgboard record);
}