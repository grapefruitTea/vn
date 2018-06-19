package com.vn.shop.web.superadmin;

import com.vn.shop.entity.Area;
import com.vn.shop.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService areaService;
    Logger logger = LoggerFactory.getLogger(AreaController.class);

    @RequestMapping(value = "/listarea",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listArea() {
        logger.info("---------------start");
        Map<String, Object> modelMap = new HashMap<>(10);
        List<Area> list;
        try {
            list = areaService.getAreaList();
            modelMap.put("rows", list);
            modelMap.put("total", list.size());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        logger.debug("-----------debug");
        logger.error("-----------error");
        logger.info("---------------end");
        return modelMap;
    }
}
