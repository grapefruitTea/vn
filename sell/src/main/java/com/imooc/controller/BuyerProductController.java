package com.imooc.controller;

import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //查询所有上架商品
        List<ProductInfo> productInfos = productService.findUpAll();

        //查询类目（一次性查询）
//        List<Integer> categoryTypes = new ArrayList<>();
//        for (ProductInfo productInfo : productInfos) {
//            categoryTypes.add(productInfo.getCategoryType());
//        }

        //lamda

        List<Integer> categoryTypes = productInfos.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());


        List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypes);
        //拼接数据

        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory productCategory:productCategories) {
            ProductVO productVO= new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOS = new ArrayList<ProductInfoVO>();
            for (ProductInfo productInfo : productInfos) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    productInfoVOS.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOS);
            productVOS.add(productVO);
        }


        return ResultVOUtil.success(productVOS);

    }
}
