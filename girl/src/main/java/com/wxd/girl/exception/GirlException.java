package com.wxd.girl.exception;

import com.wxd.girl.enums.ResultEnum;

/**
 * @Auther: vn
 * @Date: 2018/6/8 22:39
 * @Description: 不能直接继承Exception,否则Springboot 不能回滚事务
 */
public class GirlException extends RuntimeException {
    private Integer code;


    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
