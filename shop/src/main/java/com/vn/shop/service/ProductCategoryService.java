package com.vn.shop.service;

import com.vn.shop.dto.ProductCategoryExecution;
import com.vn.shop.entity.ProductCategory;
import com.vn.shop.exception.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getByShopId(long id);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
     * 将此类别对应商品的类别id置为空，再删除类别
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    ProductCategoryExecution deleteProductCategory(long  productCategoryId,long shopId) throws ProductCategoryOperationException;
}
