package com.vn.shop.service.impl;

import com.vn.shop.dao.ProductCategoryDao;
import com.vn.shop.dto.ProductCategoryExecution;
import com.vn.shop.entity.ProductCategory;
import com.vn.shop.enums.ProductCategoryStateEnum;
import com.vn.shop.exception.ProductCategoryOperationException;
import com.vn.shop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Override
    public List<ProductCategory> getByShopId(long shopId) {
        return productCategoryDao.queryByShopId(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (null != productCategoryList && productCategoryList.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    /**
     * 将此类别对应商品的类别id置为空，再删除类别
     *
     * @param productCategoryId
     * @param shopId
     * @return
     * @throws ProductCategoryOperationException
     */
    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        //todo 删除时将此类别对应商品id置空
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum > 0) {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            } else {
                throw new ProductCategoryOperationException("删除商品类别失败！");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error：" + e.getMessage());
        }
    }
}
