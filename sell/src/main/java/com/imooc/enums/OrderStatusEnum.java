package com.imooc.enums;

import lombok.Getter;

/**
 * @Auther: vn
 * @Date: 2018/6/1 21:40
 * @Description:
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消")
    ;
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
