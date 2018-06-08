package com.wxd.girl.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: vn
 * @Date: 2018/6/7 23:14
 * @Description:
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger log = LoggerFactory.getLogger(HttpAspect.class);


//    @Before("execution(public * com.wxd.girl.controller.GirlController.*(..))")
//    public void log() {
//        System.out.println("111111111111111111");
//    }
//
//    @After("execution(public * com.wxd.girl.controller.GirlController.*(..))")
//    public void doAfter() {
//        System.out.println("22222222222222");
//    }


    @Pointcut("execution(public * com.wxd.girl.controller.GirlController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        //url
        log.info("url={}", httpServletRequest.getRequestURI());
        //method
        log.info("method={}", httpServletRequest.getMethod());
        //ip
        log.info("ip={}", httpServletRequest.getRemoteAddr());
        //类方法
        log.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+
                    joinPoint.getSignature().getName());
        //参数
        log.info("args={}",joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        log.info("2222222222222222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        log.info("response={}", object.toString());
    }

}



