package com.vn.shop.service;

import com.vn.shop.dto.ImageHolder;
import com.vn.shop.dto.ProductExecution;
import com.vn.shop.entity.Product;
import com.vn.shop.exception.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    ProductExecution addProduct(Product product, ImageHolder thumbnail,
                                List<ImageHolder> productImgList) throws ProductOperationException;
}
