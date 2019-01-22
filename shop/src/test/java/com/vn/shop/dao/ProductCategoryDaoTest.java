package com.vn.shop.dao;

import com.vn.shop.BaseTest;
import com.vn.shop.entity.ProductCategory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryById() {
        long shopId = 31L;
        List<ProductCategory> productCategories = productCategoryDao.queryByShopId(shopId);
        System.out.println(productCategories.size());

    }

    @Test
    public void testBatchInsert() {
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("test类别1");
        productCategory1.setProductCategoryDesc("test描述1");
        productCategory1.setPriority(14);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(31L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("test类别2");
        productCategory2.setProductCategoryDesc("test描述2");
        productCategory2.setPriority(24);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(31L);

        List<ProductCategory> categoryList = new ArrayList<>();
        categoryList.add(productCategory1);
        categoryList.add(productCategory2);

        int result = productCategoryDao.batchInsertProductCategory(categoryList);

        Assert.assertEquals(2, result);

    }

    @Test
    public void testDeleteProductCategory() {
        int result = productCategoryDao.deleteProductCategory(22L, 31L);
        System.out.println(result);

        int result1 = productCategoryDao.deleteProductCategory(23L, 31L);
        System.out.println(result1);
    }

}