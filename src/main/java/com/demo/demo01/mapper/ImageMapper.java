package com.demo.demo01.mapper;

import com.demo.demo01.bean.Image;
import com.demo.demo01.bean.ImageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ImageMapper {
    int countByExample(ImageExample example);

    int deleteByExample(ImageExample example);

    int deleteByPrimaryKey(Integer id);
    int selectMaxId();
    int insert(Image record);
    int getTotalRecord(int uid);
    int insertSelective(Image record);
    //@Select("select * from image where uid=#{uid} limit startIndex=#{startIndex},pageSize=#{pageSize}")
    List<Image> selectByUserId(@Param("uid") int uid,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);
    List<Image> selectByExample(ImageExample example);
    Image selectByImgurl(String imgurl);
    Image selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}