package com.vn.shop.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.shop.dto.ImageHolder;
import com.vn.shop.dto.ShopExecution;
import com.vn.shop.entity.Area;
import com.vn.shop.entity.PersonInfo;
import com.vn.shop.entity.Shop;
import com.vn.shop.entity.ShopCategory;
import com.vn.shop.enums.ShopStateEnum;
import com.vn.shop.exception.ShopOperationException;
import com.vn.shop.service.AreaService;
import com.vn.shop.service.ShopCategoryService;
import com.vn.shop.service.ShopService;
import com.vn.shop.util.CodeUtil;
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
import java.util.ArrayList;
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

    @RequestMapping(value = "/getshopmanagementinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopManagementInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(8);
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId <= 0) {
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null) {
                modelMap.put("redirect", true);
                modelMap.put("url", "/shop/shopadmin/shoplist");
            } else {
                Shop currentShop = (Shop) currentShopObj;
                modelMap.put("redirect", false);
                modelMap.put("shopId", currentShop.getShopId());
            }
        } else {
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put("redirect", false);
        }


        return modelMap;
    }

    @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(8);
        //todo 暂未实现登录
        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("wxd");
        request.getSession().setAttribute("user", user);
        user = (PersonInfo) request.getSession().getAttribute("user");
        Shop shopCondition = new Shop();
        shopCondition.setOwner(user);
        try {
            ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
            modelMap.put("success", true);
            modelMap.put("shopList", shopExecution);
            modelMap.put("user", user);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }


        return modelMap;
    }

    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopById(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>(8);
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > -1) {
            try {
                List<Area> areaList = areaService.getAreaList();
                Shop shop = shopService.getByShopId(shopId);
                modelMap.put("success", true);
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

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
        if (!CodeUtil.checkVerifyCode(httpServletRequest)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码!");
            return modelMap;
        }

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
        if (null != shop && null != shopImg) {

            PersonInfo owner = (PersonInfo) httpServletRequest.getSession().getAttribute("user");
            shop.setOwner(owner);
            ShopExecution shopExecution = null;
            try {
                ImageHolder imageHolder =
                        new ImageHolder(shopImg.getInputStream(), shopImg.getOriginalFilename());
                shopExecution = shopService.addShop(shop, imageHolder);
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
                //添加店铺成功将可操作的店铺放入session
                List<Shop> shopList = (List<Shop>) httpServletRequest.getSession().getAttribute("shopList");
                if (null == shopList || shopList.isEmpty()) {
                    shopList = new ArrayList<>();
                }
                shopList.add(shopExecution.getShop());
                httpServletRequest.getSession().setAttribute("shopList", shopList);
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

    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modfiyShop(HttpServletRequest httpServletRequest) {
        Map<String, Object> modelMap = new HashMap<>(16);
        if (!CodeUtil.checkVerifyCode(httpServletRequest)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码!");
            return modelMap;
        }

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
        }

        //2.Modify Shop.更新shop，图片信息可为空，shopId不能为空
        if (null != shop && null != shop.getShopId()) {
            // TODO: 2019/1/9 interceptor中权限认证
            ShopExecution shopExecution = null;
            try {
                if (null == shopImg) {
                    shopExecution = shopService.modifyShop(shop, null);
                } else {
                    ImageHolder imageHolder =
                            new ImageHolder(shopImg.getInputStream(), shopImg.getOriginalFilename());
                    shopExecution = shopService.modifyShop(shop, imageHolder);
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", shopExecution.getStateInfo());
            }
            return modelMap;

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺Id");
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
