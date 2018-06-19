package com.vn.shop.dao;

import com.vn.shop.BaseTest;
import com.vn.shop.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void queryShopCategory() {
        List<ShopCategory> list = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(1,list.size());
    }
    @Test
    public void queryShopCategory1() {
        ShopCategory shopCategory = new ShopCategory();
        ShopCategory parentShopCategory = new ShopCategory();
        parentShopCategory.setShopCategoryId(34L);
        shopCategory.setParent(parentShopCategory);
        List<ShopCategory> list = shopCategoryDao.queryShopCategory(shopCategory);
        assertEquals(2,list.size());
    }
}