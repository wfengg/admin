package com.demo.demo01.controller;


import com.demo.demo01.bean.Msgboard;
import com.demo.demo01.bean.Replymsg;
import com.demo.demo01.service.MsgboardService;
import com.demo.demo01.service.ReplyMsgService;
import com.demo.demo01.service.UserService;
import com.demo.demo01.utils.BaseResponse;
import com.demo.demo01.utils.PageBean;
import com.demo.demo01.utils.SetDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedList;
import java.util.List;

@Controller
@Api(value = "管理员")
public class UserIadminController {
    @Autowired
    @Qualifier("msgboardService")
    private MsgboardService msgboardService;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Autowired
    @Qualifier("replyMsgService")
    ReplyMsgService replyMsgService;

    @ApiOperation("管理留言")
    @ResponseBody
    @PostMapping("/showAllMsg")
    public Object showAllMsg(@RequestParam("pageNumber")int pageNumber){
        int pageSize = 3;
        List<String> username = new LinkedList<>();
        int totalRecord = msgboardService.getTotalRecords();
        PageBean<Msgboard> pageBean = new PageBean<>(pageNumber,pageSize,totalRecord);
        List<Msgboard> list = msgboardService.selectAll(pageBean.getStartIndex(),pageSize);
        for(int i = 0;i<list.size();i++){
            if(list.get(i).getUserid()==null){
                username.add("none");
            }else {
                String name = userService.selectById(list.get(i).getUserid()).getUsername();
                username.add(name);

            }
        }
        pageBean.setData(list);
        pageBean.setUsername(username);
        System.out.println(pageBean);
        return pageBean;
    }


    @ApiOperation("删除留言")
    @ResponseBody
    @PostMapping("/deleteMsg")
    public BaseResponse delete(@RequestParam("id")Integer id){
        int n = msgboardService.deleteById(id);
        if(n!=0){
            return new BaseResponse();
        }
        return new BaseResponse(-1,"erro");
    }


    @ApiOperation("回复留言")
    @ResponseBody
    @PostMapping(value = "/replyMsg",consumes = "application/json")
    public BaseResponse reply(@RequestBody Replymsg replymsg){
           String date = SetDate.getNowDate("yyyy-MM-dd HH:mm:ss");
           replymsg.setDate(date);
           Integer m = replyMsgService.insertReply(replymsg);
           if(m==null){
               return new BaseResponse(-1,"invalied reply");
           }
           return new BaseResponse();
    }
}
