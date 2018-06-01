package com.imooc.enums;

import lombok.Getter;

/**
 * 商品状态枚举
 */
@Getter
public enum ProductStatuseEnum {

    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    private Integer code;
    private String message;


    ProductStatuseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
