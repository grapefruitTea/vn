package com.wxd.girl.handle;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import com.wxd.girl.dataobject.Result;
import com.wxd.girl.exception.GirlException;
import com.wxd.girl.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: vn
 * @Date: 2018/6/8 22:25
 * @Description:
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger log = LoggerFactory.getLogger(ExceptionHandle.class);
    //声明要监控或处理的是哪个异常类
    @ExceptionHandler(value = Exception.class)
    //此处不是controller，需标志responsebody
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            return ResultUtil.error(((GirlException) e).getCode(), e.getMessage());
        } else {
            log.error("【系统异常】{}",e);
            return ResultUtil.error(-1, "未知错误");
        }

    }
}
