package com.vn.shop.service.impl;

import ch.qos.logback.core.util.FileUtil;
import com.vn.shop.dao.ProductDao;
import com.vn.shop.dao.ProductImgDao;
import com.vn.shop.dto.ImageHolder;
import com.vn.shop.dto.ProductExecution;
import com.vn.shop.entity.Product;
import com.vn.shop.entity.ProductImg;
import com.vn.shop.enums.ProductStateEnum;
import com.vn.shop.exception.ProductOperationException;
import com.vn.shop.service.ProductService;
import com.vn.shop.util.ImageUtil;
import com.vn.shop.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail
            , List<ImageHolder> productImgList) throws ProductOperationException {
        //1.获取缩略图相对路径，赋值给Product
        //2.给tb_product表写入信息，并获取productId
        //3.根据productId批量处理商品详情图
        //4.将商品详情图插入tb_product_img
        if (null != product && null != product.getShop() && null != product.getShop().getShopId()) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认上架
            product.setEnableStatus(1);
            //处理缩略图
            if (null != thumbnail) {
                addThumbnail(product, thumbnail);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败!");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败：" + e.getMessage());
            }

            //商品详情图不为空再添加
            if (null != productImgList && productImgList.size() > 0) {
                addProductList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void addProductList(Product product, List<ImageHolder> productImgHolderList) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        if (productImgHolderList != null && productImgHolderList.size() > 0) {
            List<ProductImg> productImgList = new ArrayList<>();
            for (ImageHolder imageHolder : productImgHolderList) {
                ProductImg productImg = new ProductImg();
                String imgAddr = ImageUtil.generateNormalImg(imageHolder, dest);
                productImg.setImgAddr(imgAddr);
                productImg.setProductId(product.getProductId());
                productImg.setCreateTime(new Date());
                productImgList.add(productImg);
            }
            if (productImgList.size() > 0) {
                try {
                    int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                    if (effectedNum <= 0) {
                        throw new RuntimeException("创建商品详情图片失败");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("创建商品详情图片失败:" + e.toString());
                }
            }
        }
    }

    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String shopImagePath = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr =
                ImageUtil.generateThumbnail(thumbnail,
                        shopImagePath);
        product.setImgAddr(thumbnailAddr);
    }
}
