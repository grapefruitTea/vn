package com.vn.shop.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Area;
import com.vn.shop.entity.Shop;
import com.vn.shop.entity.ShopCategory;
import com.vn.shop.enums.ShopStateEnum;
import com.vn.shop.service.AreaService;
import com.vn.shop.service.ShopCategoryService;
import com.vn.shop.service.ShopService;
import com.vn.shop.util.HttpServletRequestUtil;
import com.vn.shop.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getInitInfo() {
        Map<String, Object> modelMap = new HashMap<>(16);
        List<ShopCategory> shopCategoryList;
        List<Area> areaList;
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("success", true);
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }


        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest httpServletRequest) {
        Map<String, Object> modelMap = new HashMap<>(16);
        //1.Accepts and transforms parameters.
        String shopStr = HttpServletRequestUtil.getString(httpServletRequest, "shopStr");
        if (null == shopStr) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "Shop info has error!");
            return modelMap;
        }

        Shop shop = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(httpServletRequest)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
        }

        //2.Register Shop.
        File shopImgFile = new File(PathUtil.getImgBasePath() + PathUtil.getRandomFileName());
        try {
            shopImgFile.createNewFile();
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }


        if (null != shop && null != shopImg) {
            ShopExecution shopExecution = null;
            try {
                shopExecution = shopService.addShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", shopExecution.getStateInfo());
            }
            return modelMap;

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入信息");
            return modelMap;
        }
        //3.Returns the result.

    }

//    private static void inputStreamToFile(InputStream ins, File file) {
//        FileOutputStream fos = null;
//
//        try {
//            fos = new FileOutputStream(file);
//            byte[] buffer = new byte[1024];
//            int bytesRead = 0;
//            while ((bytesRead = ins.read(buffer)) != -1) {
//                fos.write(buffer, 0, bytesRead);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("调用inputStreamToFile发生异常： " + e.getMessage());
//        } finally {
//            if (null != fos) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    throw new RuntimeException("inputStreamToFile关闭io发生异常： " + e.getMessage());
//                }
//            }
//            if (null != ins) {
//                try {
//                    ins.close();
//                } catch (IOException e) {
//                    throw new RuntimeException("inputStreamToFile关闭io发生异常： " + e.getMessage());
//                }
//            }
//        }
//
//    }
}
