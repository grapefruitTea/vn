package com.imooc.enums;

import lombok.Getter;

/**
 * @Auther: vn
 * @Date: 2018/6/1 21:46
 * @Description:paystatus enum
 */
@Getter
public enum PayStatusEnum {

    WAIT(0, "未支付"),
    SUCCESS(1, "支付成功");


    private Integer code;
    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
