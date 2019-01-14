package com.vn.shop.service;

import com.vn.shop.dto.ProductCategoryExecution;
import com.vn.shop.entity.ProductCategory;
import com.vn.shop.exception.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> getByShopId(long id);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;
}
