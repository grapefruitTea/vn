package com.imooc.enums;

import lombok.Getter;

/**
 * @Auther: vn
 * @Date: 2018/6/12 00:00
 * @Description:
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(100,"商品不存在"),
    PRODUCT_UNDERSTOCK(101,"商品库存不足"),
    ORDER_NOT_EXIST(102,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(103,"订单详情不存在"),
    ORDER_STATUS_ERROR(104,"订单状态不正确"),
    ORDER_UPDATE_FAIL(105,"订单更新失败"),
    ORDER_DETAIL_EMPTY(106,"订单详情为空")
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
