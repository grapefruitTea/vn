package com.vn.shop.service.impl;

import com.vn.shop.BaseTest;
import com.vn.shop.dto.ImageHolder;
import com.vn.shop.dto.ProductExecution;
import com.vn.shop.entity.Product;
import com.vn.shop.entity.ProductCategory;
import com.vn.shop.entity.Shop;
import com.vn.shop.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceImplTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void addProduct() throws FileNotFoundException {
        Shop shop1 = new Shop();
        shop1.setShopId(31L);
        Shop shop2 = new Shop();
        shop2.setShopId(32L);
        ProductCategory pc1 = new ProductCategory();
        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试Desc1");
        product1.setPriority(0);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);

        File file = new File("D:\\projectres\\vn.png");
        InputStream is = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(is, file.getName());


        File file1 = new File("D:\\projectres\\0.png");
        InputStream is1 = new FileInputStream(file1);
        ImageHolder imageHolder1 = new ImageHolder(is1, file1.getName());
        File file2 = new File("D:\\projectres\\1.png");
        InputStream is2 = new FileInputStream(file2);
        ImageHolder imageHolder2 = new ImageHolder(is2, file2.getName());

        List<ImageHolder> imageHolders = new ArrayList<>();
        imageHolders.add(imageHolder1);
        imageHolders.add(imageHolder2);


        ProductExecution productExecution = productService.addProduct(product1, imageHolder, imageHolders);
        System.out.println(productExecution.getCount());


    }
}