package com.vn.o2o.mapper;

import com.vn.o2o.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaMapperTest {
    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void queryTest() {
        List<Area> list = areaMapper.queryAllArea();
        System.out.println(list.size());
    }
}