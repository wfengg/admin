package com.demo.demo01.controller;


import com.demo.demo01.bean.Msgboard;
import com.demo.demo01.bean.User;
import com.demo.demo01.service.MsgboardService;
import com.demo.demo01.service.UserService;

import com.demo.demo01.utils.BaseResponse;
import com.demo.demo01.utils.DealFileName;
import com.demo.demo01.utils.SetDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Api(value = "登陆和注册")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("msgboardService")
    private MsgboardService msgboardService;

    @ApiOperation("登陆")
    @ResponseBody
    @PostMapping(value = "/login",consumes = "application/json")
    public BaseResponse login(@RequestBody User user, HttpSession session){
        Integer i  = userService.selectByUser(user);
        if(i==null){
            return new BaseResponse(-1,"NoSuchUser");
        }
        if(user.getUsername().equals("admin")&&user.getPassword().equals("999")){
            session.setAttribute("id",i);
            return new BaseResponse(3,"adminLogin");
        }
        User user1 = userService.selectById(i);
        session.setAttribute("username",user1.getUsername());
        session.setAttribute("headUrl",user1.getHeadurl());
        session.setAttribute("id",user1.getId());
        return  new BaseResponse();
    }



    @ApiOperation("注册用户")
    @ResponseBody
    @PostMapping(value = "/register",consumes = "application/json")
    public BaseResponse register(@RequestBody User user){
        boolean m = userService.exist(user.getUsername());
        if(!m||user.getUsername()==null||user.getPassword()==null||user.getHeadurl()==null) {
            return new BaseResponse(-1, "SomethingWrong");
        }
        if(user.getUsername().length()>10||user.getPassword().length()>10){
            return new BaseResponse(-2,"TooLong");
        }
        else {
            userService.insert(user);
            return new BaseResponse();
        }
    }



    @ApiOperation("上传头像")
    @ResponseBody
    @PostMapping("/upload")
    public  BaseResponse upload(@RequestParam("headurl")MultipartFile file) {
        String local = "C:/Users/王凤国/IdeaProjects/demo01/src/main/resources/static/photo/";
        String suffix = DealFileName.checkFile(file);
        if(suffix.equals("jpg")||suffix.equals("png")){
            boolean m = DealFileName.insertImg(file,local);
            String msg = "/photo/"+ DealFileName.getFileName(file);
            return new BaseResponse(1,msg);
        }
        return new BaseResponse(-1,"ImgFalse");
    }



    @ApiOperation("注册成功")
    @ResponseBody
    @GetMapping("/success")
    public Object success(HttpSession session){
        String username = (String) session.getAttribute("username");
        String headUrl = (String)session.getAttribute("headUrl");
        User user = new User();
        user.setUsername(username);
        user.setHeadurl(headUrl);
        return user;
    }

    @ApiOperation("添加留言")
    @ResponseBody
    @PostMapping(value = "/firstLeave",consumes = "application/json")
    public BaseResponse insertWords(@RequestBody Msgboard msgboard, HttpSession session){
        String date = SetDate.getNowDate("yyyy-MM-dd HH:mm:ss");
        String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        int i = 0;
        if(StringUtils.isEmpty(msgboard.getWords())||StringUtils.isEmpty(msgboard.getTel())) {
            return new BaseResponse(-2,"invalid parameters");
        }
        if(msgboard.getTel().toString().length()!=11||!msgboard.getTel().toString().matches(PHONE_NUMBER_REG)){
            return new BaseResponse(-3,"invalid tel");
        }

        if(session.getAttribute("id")!=null){
            Integer userId = (Integer) session.getAttribute("id");
            Msgboard words = new Msgboard(msgboard.getWords(),msgboard.getTel(),userId,date);
            i = msgboardService.insertSelective(words);
        }else {
            Msgboard words = new Msgboard(msgboard.getWords(),msgboard.getTel(),date);
            i = msgboardService.insertSelective(words);
        }
        if(i>0) {
            return new BaseResponse();
        }
        return new BaseResponse(-1,"failed");

    }



    }
