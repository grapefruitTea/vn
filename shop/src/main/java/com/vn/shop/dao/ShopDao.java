package com.vn.shop.dao;

import com.vn.shop.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺DAO
 *
 * @author Administrator
 */
public interface ShopDao {
    /**
     * 分页查询店铺信息，条件参数有：店铺名称（支持模糊），店铺状态，店铺类别，区域信息，owner
     * @param shopCondition
     * @param rowIndex 从第几行开始取
     * @param pageSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
                             @Param("pageSize") int pageSize);

    /**
     * 获取满足条件的shopList总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
    /**
     * 根据shopId查询店铺信息
     *
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);

    /**
     * 注册店铺
     *
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     *
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
