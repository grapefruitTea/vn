package com.wxd.girl.utils;

import com.sun.net.httpserver.Authenticator;
import com.wxd.girl.dataobject.Result;

/**
 * @Auther: vn
 * @Date: 2018/6/8 00:13
 * @Description:
 */
public class ResultUtil {
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    public static Result success(){

        return success(null);
    }

    public static Result error(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
