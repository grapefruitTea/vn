package com.vn.shop.dao;

import com.vn.shop.BaseTest;
import com.vn.shop.entity.Area;
import com.vn.shop.entity.PersonInfo;
import com.vn.shop.entity.Shop;
import com.vn.shop.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void queryShop() {
        Shop shopCondition = new Shop();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        shopCondition.setOwner(personInfo);
        List<Shop> shops = shopDao.queryShopList(shopCondition, 0, 5);
        System.out.println(shops.size());
        int i = shopDao.queryShopCount(shopCondition);
        System.out.println("I= " + i);
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(37L);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shops1 = shopDao.queryShopList(shopCondition, 0, 2);
        int j = shopDao.queryShopCount(shopCondition);
        System.out.println("mut -----------"+shops1.size()+"  "+j);
    }

    @Test
    public void insertShop() {
        Area area = new Area();
        PersonInfo owner = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(2L);
        owner.setUserId(1L);
        shopCategory.setShopCategoryId(34L);
        Shop shop = new Shop();
        shop.setAdvice("加糖");
        shop.setArea(area);
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setLastEditTime(new Date());
        shop.setPhone("18717322343");
        shop.setPriority(1);
        shop.setShopAddr("拐弯200米");
        shop.setShopCategory(shopCategory);
        shop.setShopDesc("好喝的果汁");
        shop.setShopImg("果汁地址");
        shop.setShopName("果汁街");
        shop.setOwner(owner);

        int result = shopDao.insertShop(shop);

        assertEquals(1, result);

    }

    @Test
    public void updateShop() {
        Shop shop = new Shop();
        shop.setShopId(31l);
        shop.setShopAddr("拐弯100米");
        shop.setShopImg("奶茶铺图片链接");
        shop.setShopName("绿茶街角");

        int i = shopDao.updateShop(shop);
        assertEquals(1, i);
    }

    @Test
    public void queryByShopId() {
        Shop shop = shopDao.queryByShopId(31);
    }
}