package com.demo.demo01.controller;

import com.demo.demo01.bean.Image;
import com.demo.demo01.service.ImageService;
import com.demo.demo01.utils.BaseResponse;
import com.demo.demo01.utils.DealFileName;
import com.demo.demo01.utils.PageBean;
import com.demo.demo01.utils.SetDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Api(value = "编辑相册")
public class UserImagesController {
    @Autowired
    @Qualifier("imageService")
    private ImageService imageService;


    @ApiOperation("个人相册")
    @ResponseBody
    @PostMapping("/show")
    public Object show(@RequestParam("pageNumber")int pageNumber, HttpSession session){
        Integer userId = (Integer) session.getAttribute("id");
        int pageSize = 3;
        int totalRecord = imageService.getTotalRecord(userId);
        PageBean<Image> pageBean = new PageBean<>(pageNumber,pageSize,totalRecord);
        List<Image> list = imageService.selectByUserId(userId,pageBean.getStartIndex(),pageSize);
        pageBean.setData(list);
        return pageBean;
    }


    @ApiOperation("添加图片")
    @ResponseBody
    @PostMapping("/updateImg")
    public Object updateImg(@RequestParam("img")MultipartFile file , HttpSession session){
        int uid = (int)session.getAttribute("id");
        String date = SetDate.getNowDate("yyyy-MM-dd HH:mm:ss");
        String local = "C:/Users/王凤国/IdeaProjects/demo01/src/main/resources/static/photo/";
        String suffix = DealFileName.checkFile(file);
        long filesize = DealFileName.getImgSize(file);
        if(suffix.equals("jpg")||suffix.equals("png")){
           DealFileName.insertImg(file,local);
           String msg ="/photo/"+DealFileName.getFileName(file);
           Image image = new Image(uid,msg,filesize,date);
           imageService.insertSelective(image);
           int maxId = imageService.selectMaxId();
           Image image1 = imageService.selectByPrimaryKey(maxId);
           return image1;
        }
       return null;
    }


    @ApiOperation("删除图片")
    @ResponseBody
    @PostMapping(value = "/deleteImg")
    public BaseResponse deleteImg(@RequestParam("id") int id){
        int n = imageService.deleteById(id);
        if(n!=0){
            return new BaseResponse();
        }
        return new BaseResponse(-1,"erro");
    }

}
