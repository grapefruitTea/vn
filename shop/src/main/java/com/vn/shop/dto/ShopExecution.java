package com.vn.shop.dto;

import com.vn.shop.entity.Shop;
import com.vn.shop.enums.ShopStateEnum;

import java.util.List;

/**
 *  店铺信息传输层
 */
public class ShopExecution {
    /**
     * 结果状态
     */
    private int state;
    /**
     * 状态信息
     */

    private String stateInfo;
    /**
     * 店铺数量
     */
    private int count;
    /**
     * 操作的Shop 增删改时用到
     */
    private Shop shop;
    /**
     * Shop列表 查询时用到
     */
    private List<Shop> shops;

    public ShopExecution() {
    }

    /**
     * 店铺操作失败时用到
     */
    public ShopExecution(ShopStateEnum shopStateEnum) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
    }

    /**
     * 店铺操作成功时用到
     */
    public ShopExecution(ShopStateEnum shopStateEnum, Shop shop) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 店铺查询成功时用到
     */
    public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> shops) {
        this.state = shopStateEnum.getState();
        this.stateInfo = shopStateEnum.getStateInfo();
        this.shops = shops;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
