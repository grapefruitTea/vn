package com.vn.shop.web.shopadmin;

import com.vn.shop.dto.ProductCategoryExecution;
import com.vn.shop.dto.Result;
import com.vn.shop.entity.ProductCategory;
import com.vn.shop.entity.Shop;
import com.vn.shop.enums.ProductCategoryStateEnum;
import com.vn.shop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        Shop currentShop = (Shop) request.getSession().getAttribute(
                "currentShop");
        List<ProductCategory> list = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            list = productCategoryService.getByShopId(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, list);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, ps.getState(),
                    ps.getStateInfo());
        }
    }

    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,
                                                   HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(8);
        Shop currentShop = (Shop) request.getSession().getAttribute(
                "currentShop");
        if (productCategoryList != null && productCategoryList.size() > 0) {
            for (ProductCategory productCategory : productCategoryList) {
                productCategory.setShopId(currentShop.getShopId());
            }
            try {
                ProductCategoryExecution pe = productCategoryService
                        .batchAddProductCategory(productCategoryList);
                if (pe.getStatus() == ProductCategoryStateEnum.SUCCESS
                        .getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }


        return modelMap;
    }

}
