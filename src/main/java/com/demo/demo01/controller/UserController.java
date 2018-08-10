package com.demo.demo01.controller;

import com.demo.demo01.bean.User;
import com.demo.demo01.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


//@RestController
@Controller
@Api(value = "login and register")
public class UserController {

    @Autowired
    private UserService userService;
    @ApiOperation("登陆")
    @ApiImplicitParam(paramType = "body",dataType ="User",name = "user",value = "login ",required = true)
    @ResponseBody
    @PostMapping(value = "/login",consumes = "application/json")
    public Integer login(@RequestBody User user){
        System.out.println(user.toString());
        Integer i  = userService.selectByUser(user);
        System.out.println(i);
         return  i;
    }

























    @ResponseBody
    @PostMapping(value = "/register",consumes = "application/json")
    public Boolean reg(@RequestBody User user){
        System.out.println(user.toString());
//        boolean m = userService.exist(user.getUsername());
//        if(!m){
//            userService.insert(user);
//        }
        return true;
    }

//    @ResponseBody
//    @GetMapping("/userInfo/get")
//    public  Object getUserInfo( HttpSession session) {
//
//    }





    @ResponseBody
    @PostMapping("/upload")
    public  String upload(@RequestParam("file")MultipartFile file, HttpSession session) {
            String  msg ="";
            String local = "C:/Users/王凤国/IdeaProjects/demo01/src/main/resources/static/photo/";
            String filename = file.getOriginalFilename();
            if(!filename.contains(".jpg")){
                msg = "失败";
                return msg;
            }
            File serverFile = new File(local+filename);
            try {
                file.transferTo(serverFile);
//                FileOutputStream out = new FileOutputStream(serverFile);
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
                System.out.println("文件已保存");
                msg ="/photo/"+filename;
                return msg;
            } catch (IOException e) {
                e.printStackTrace();
                msg ="失败";
                return msg;
            }
        }

    }
