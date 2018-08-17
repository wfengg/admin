package com.demo.demo01.aop;


import com.demo.demo01.utils.BaseResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class LoginAspect {

//    * execution(* com.sample.service.impl..*.*(..))
//    解释如下：
//    符号	含义
//    execution（）
//    表达式的主体；
//    第一个”*“符号
//    表示返回值的类型任意；
//    com.sample.service.impl	AOP所切的服务的包名，即，我们的业务部分
//    包名后面的”..“	表示当前包及子包
//    第二个”*“	表示类名，*即所有类。此处可以自定义，下文有举例
//    .*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型
    @Pointcut("execution(public * com.demo.demo01.controller.UserI*.*(..))")
    public void webAspect(){}

    @Around("webAspect()")
    public Object doBefore(ProceedingJoinPoint joinPoint){
        //工具类RequestContextHolder，能够在Controller中获取request对象
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        //System.out.println(session.getAttribute("id"));
        Integer id = (Integer)session.getAttribute("id");
        if(id==null){
            return new BaseResponse(100,"NotLogin");
        }
        try {

            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
}
