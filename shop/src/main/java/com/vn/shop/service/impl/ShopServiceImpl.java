package com.vn.shop.service.impl;

import com.vn.shop.dao.ShopDao;
import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Shop;
import com.vn.shop.enums.ShopStateEnum;
import com.vn.shop.exception.ShopOperationException;
import com.vn.shop.service.ShopService;
import com.vn.shop.util.ImageUtil;
import com.vn.shop.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;



/**
 * shop service层
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String fileName) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            //初始化默认店铺信息
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (null != shopImgInputStream) {
                    try {
                        //存储图片
                        addShopImg(shop, shopImgInputStream,fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error: " + e.getMessage());
                    }

                }
                effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    throw new ShopOperationException("更新店铺图片失败");
                }

            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }


        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
        String shopImagePath = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, shopImagePath,fileName);

        shop.setShopImg(shopImgAddr);
    }
}
