package com.demo.demo01.service;

import com.demo.demo01.bean.Image;
import com.demo.demo01.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ImageService {

    @Autowired
    @Qualifier("imageMapper")
    private ImageMapper imageMapper;
    public List<Image> selectByUserId(Integer uid,Integer startIndex,Integer pageSize){
         return imageMapper.selectByUserId(uid,startIndex,pageSize);
    }

    public int insertSelective(Image image){
        return imageMapper.insertSelective(image);
    }

    public Image selectByImgurl(String imgurl){
        return  imageMapper.selectByImgurl(imgurl);
    }

    public int deleteById(int id){
        return imageMapper.deleteByPrimaryKey(id);
    }

    public int selectMaxId(){return imageMapper.selectMaxId();}

    public Image selectByPrimaryKey(int id){
        return imageMapper.selectByPrimaryKey(id);
    }

    public int getTotalRecord(int uid){
        return imageMapper.getTotalRecord(uid);
    }
}
