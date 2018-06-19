package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * @Auther: vn
 * @Date: 2018/6/11 23:59
 * @Description:
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }
}
