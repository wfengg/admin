package com.demo.demo01.controller;

import com.demo.demo01.bean.User;
import com.demo.demo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@RestController
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/html")
    public String html(){
        return "success";
    }






    @PostMapping(value = "/login",consumes = "application/json")
    public String login(@RequestBody User user){
        user.getUsername();
        System.out.println(user.toString());
        Integer i  = userService.findTUser(user);
         System.out.println(i);
       if (i.equals(0)){
            return "failed";
        }
            return "success";
    }













    @PostMapping("/register")
    @ResponseBody
    public Boolean register(@RequestBody User user){
        userService.insert(user);
        boolean isExist = userService.exist(user.getUsername());
        System.out.println(isExist);
        return isExist;
    }
}
