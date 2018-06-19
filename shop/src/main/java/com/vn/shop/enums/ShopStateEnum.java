package com.vn.shop.enums;

/**
 * 店铺信息状态枚举
 */
public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(1, "非法店铺"), SUCCESS(1, "操作成功"),
    PASS(2, "通过认证"), INNER_ERROR(-1001, "系统内部错误"),
    NULL_SHOPID(-1002, "ShopId为空"),NULL_SHOP(-1003,"店铺信息为空");
    private int state;
    private String stateInfo;


    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (state == stateEnum.getState()) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
