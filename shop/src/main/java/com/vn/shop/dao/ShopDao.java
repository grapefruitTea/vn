package com.vn.shop.dao;

import com.vn.shop.entity.Shop;

/**
 * 店铺DAO
 * @author Administrator
 */
public interface ShopDao {
    /**
     * 根据shopId查询店铺信息
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);
    /**
     * 注册店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
