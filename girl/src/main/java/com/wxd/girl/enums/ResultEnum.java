package com.wxd.girl.enums;

/**
 * @Auther: vn
 * @Date: 2018/6/8 23:00
 * @Description:
 */
public enum ResultEnum {
    UNKNOW(-1, "未知错误"),
    PRIMARY_SCHOOL(100, "在上小学"),
    MIDDLE_SCHOOL(101, "在上初中"),
    SUCESS(0, "成功");
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
}
