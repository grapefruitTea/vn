package com.vn.shop.service.impl;

import com.vn.shop.dao.ShopDao;
import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Shop;
import com.vn.shop.enums.ShopStateEnum;
import com.vn.shop.exception.ShopOperationException;
import com.vn.shop.service.ShopService;
import com.vn.shop.util.ImageUtil;
import com.vn.shop.util.PageCalculator;
import com.vn.shop.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


/**
 * shop service层
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    /**
     * 根据查询条件，分页返回查询到的数据
     *
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution shopExecution = new ShopExecution();
        if (null != shopList) {
            shopExecution.setShops(shopList);
            shopExecution.setCount(count);
        } else {
            shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }

        return shopExecution;
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
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
                        addShopImg(shop, shopImgInputStream, fileName);
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


        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    /**
     * 更新店铺 并处理图片
     *
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        if (null == shop || null == shop.getShopId()) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //判断是否需要处理图片
            if (null != shopImgInputStream && !"".equals(fileName) && null != fileName) {
                Shop shopTemp = shopDao.queryByShopId(shop.getShopId());
                if (null != shopTemp) {
                    ImageUtil.deleteFileOrPath(shopTemp.getShopImg());
                }
                addShopImg(shop, shopImgInputStream, fileName);
            }
            //更新店铺信息
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.updateShop(shop);
            if (effectedNum <= 0) {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            } else {
                shop = shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS, shop);
            }
        } catch (Exception e) {
            throw new ShopOperationException("modifyShop error: " + e.getMessage());
        }
    }

    /**
     * 根据店铺id查询店铺信息
     *
     * @param shopId
     * @return
     */
    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        String shopImagePath = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, shopImagePath, fileName);

        shop.setShopImg(shopImgAddr);
    }
}
