package com.vn.shop.dto;

import com.vn.shop.entity.ProductCategory;
import com.vn.shop.enums.ProductCategoryStateEnum;

import java.util.List;

public class ProductCategoryExecution {
    //结果状态
    private int status;

    //状态标识
    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    /**
     * 操作失败时用到
     *
     * @param productCategoryStateEnum
     */
    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
        this.status = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
    }

    /**
     * 操作成功时用到
     *
     * @param productCategoryStateEnum
     * @param productCategoryList
     */
    public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum, List<ProductCategory> productCategoryList) {
        this.status = productCategoryStateEnum.getState();
        this.stateInfo = productCategoryStateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
