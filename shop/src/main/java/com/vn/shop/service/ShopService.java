package com.vn.shop.service;

import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Shop;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName);
}
