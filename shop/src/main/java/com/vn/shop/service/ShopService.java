package com.vn.shop.service;

import com.vn.shop.dto.ImageHolder;
import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Shop;
import com.vn.shop.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 根据查询条件，分页返回查询到的数据
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
    /**
     * 添加店铺 并处理图片
     *
     * @param shop
     * @param imageHolder
     * @return
     */
    ShopExecution addShop(Shop shop, ImageHolder imageHolder);

    /**
     * 更新店铺 并处理图片
     *
     * @param shop
     * @param imageHolder
     * @return
     */
    ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException;

    /**
     * 根据店铺id查询店铺信息
     *
     * @param shopId
     * @return 
     */
    Shop getByShopId(long shopId) throws ShopOperationException;
}
