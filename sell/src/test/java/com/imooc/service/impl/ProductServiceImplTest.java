package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123");
        Assert.assertEquals("123", productInfo.getProductId());

    }

    @Test
    public void findUpAll() {
        List<ProductInfo> list =  productService.findUpAll();
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);

    Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);

//        System.out.println(productInfoPage.getTotalElements());

        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
}

    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setProductId("456");
        info.setProductName("皮蛋粥");
        info.setProductPrice(new BigDecimal(6.5));
        info.setProductStock(200);
        info.setProductDescription("很好吃的皮蛋");
        info.setProductIcon("http://y.jpg");
        info.setCategoryType(1);
        info.setProductStatus(0);

        productService.save(info);
    }
}