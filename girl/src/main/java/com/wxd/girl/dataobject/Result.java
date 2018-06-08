package com.wxd.girl.dataobject;

/**
 * @Auther: vn
 * @Date: 2018/6/8 00:07
 * @Description:
 */
public class Result<T> {
    private Integer code;
    private  String msg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
