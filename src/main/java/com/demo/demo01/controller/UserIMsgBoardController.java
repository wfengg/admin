package com.demo.demo01.controller;

import com.demo.demo01.bean.Msgboard;
import com.demo.demo01.bean.Replymsg;
import com.demo.demo01.service.MsgboardService;
import com.demo.demo01.service.ReplyMsgService;
import com.demo.demo01.utils.BaseResponse;
import com.demo.demo01.utils.PageBean;
import com.demo.demo01.utils.SetDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Api(value = "留言板")
public class UserIMsgBoardController {

    @Autowired
    @Qualifier("msgboardService")
    private MsgboardService msgboardService;

    @Autowired
    @Qualifier("replyMsgService")
    private ReplyMsgService replyMsgService;

    @ApiOperation("添加留言")
    @ResponseBody
    @PostMapping(value = "/leave",consumes = "application/json")
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


    @ApiOperation("展示留言")
    @ResponseBody
    @PostMapping("/showmsg")
    public Object showmsg(@RequestParam("pageNumber")int pageNumber, HttpSession session){
        int userid = (int)session.getAttribute("id");
        int pageSize = 3;
        int totalRecord = msgboardService.getTotalRecord(userid);
        PageBean<Msgboard> pageBean = new PageBean<>(pageNumber,pageSize,totalRecord);
        List<Msgboard> list = msgboardService.selectByUserId(userid,pageBean.getStartIndex(),pageSize);
        pageBean.setData(list);
        return pageBean;

    }


    @ApiOperation("展示回复")
    @ResponseBody
    @GetMapping("/myReply")
    public Object showReply(HttpSession session){
        Integer userid = (Integer)session.getAttribute("id");
        List<Replymsg> list = replyMsgService.selectByUserId(userid);
        if(list.size()==0){
            return new BaseResponse(-1,"notExist");
        }
        List<Msgboard> list1 = new ArrayList<>();
        for(int i = 0;i<list.size();i++) {
                Msgboard msgboard = msgboardService.selectById(list.get(i).getMsgid());
                list1.add(msgboard);
        }
        Map<String,List> map = new HashMap<>();
        map.put("list1",list);
        map.put("list2",list1);
        System.out.println(map);
        return map;
    }

}
