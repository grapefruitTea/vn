package com.vn.shop.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation", method = RequestMethod.GET)
    public String shopOperation() {
        System.out.println("----");
        return "shop/shopoperation";
    }
}
