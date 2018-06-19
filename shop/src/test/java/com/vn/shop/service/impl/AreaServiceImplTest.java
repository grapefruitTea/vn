package com.vn.shop.service.impl;

import com.vn.shop.BaseTest;
import com.vn.shop.entity.Area;
import com.vn.shop.service.AreaService;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class AreaServiceImplTest extends BaseTest {

    @Autowired
    private AreaService areaService;
    @Test
    public void queryArea() {
        List<Area> list =areaService.getAreaList();
        assertEquals("西桃园",list.get(0).getAreaName());
    }
}