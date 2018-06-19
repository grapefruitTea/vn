package com.vn.shop.service.impl;

import com.vn.shop.BaseTest;
import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Area;
import com.vn.shop.entity.PersonInfo;
import com.vn.shop.entity.Shop;
import com.vn.shop.entity.ShopCategory;
import com.vn.shop.service.ShopService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceImplTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void addShop() throws FileNotFoundException {
        Area area = new Area();
        PersonInfo owner = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(2L);
        owner.setUserId(1L);
        shopCategory.setShopCategoryId(34L);
        Shop shop = new Shop();
        shop.setAdvice("苦咖啡1111");
        shop.setArea(area);
        shop.setPhone("12717334633");
        shop.setPriority(1);
        shop.setShopAddr("足浴对面");
        shop.setShopCategory(shopCategory);
        shop.setShopDesc("醇厚的咖啡111111");
        shop.setShopName("咖啡街");
        shop.setOwner(owner);

        File file = new File("D:\\projectres\\vn.png");
        InputStream is = new FileInputStream(file);
        ShopExecution result = shopService.addShop(shop,is, file.getName());


        assertEquals(0, result.getState());

    }
}