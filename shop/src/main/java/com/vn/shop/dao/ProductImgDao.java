package com.vn.shop.dao;

import com.vn.shop.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    int batchInsertProductImg(List<ProductImg> productImgList);
}
