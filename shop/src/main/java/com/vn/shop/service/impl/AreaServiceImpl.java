package com.vn.shop.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.shop.cache.JedisUtil;
import com.vn.shop.dao.AreaDao;
import com.vn.shop.entity.Area;
import com.vn.shop.exception.AreaOperationException;
import com.vn.shop.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private JedisUtil.Strings jedisStrings;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private AreaDao areaDao;

    private static String AREALISTKEY = "arealist";
    private static final Logger LOGGER = LoggerFactory.getLogger(AreaServiceImpl.class);
    @Override
    public List<Area> getAreaList() {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        ObjectMapper mapper = new ObjectMapper();
        if (!jedisKeys.exists(key)) {
            areaList = areaDao.queryArea();
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                LOGGER.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory()
                    .constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
