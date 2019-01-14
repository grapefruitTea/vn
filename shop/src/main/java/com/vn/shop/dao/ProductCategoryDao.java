package com.vn.shop.dao;

import com.vn.shop.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {
    List<ProductCategory> queryByShopId(long shopId);

    /**
     * 批量添加商品类别
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
}
