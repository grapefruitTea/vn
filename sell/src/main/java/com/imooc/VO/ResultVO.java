package com.imooc.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 */
@Data
public class ResultVO<T> {
    //错误码
    private Integer code;
    /**/
    private String msg;
    private T data;
}
