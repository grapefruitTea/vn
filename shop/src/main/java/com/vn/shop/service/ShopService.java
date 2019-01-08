package com.vn.shop.service;

import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Shop;
import com.vn.shop.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    /**
     * 添加店铺 并处理图片
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);

    /**
     * 更新店铺 并处理图片
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    /**
     * 根据店铺id查询店铺信息
     *
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId) throws ShopOperationException;
}
