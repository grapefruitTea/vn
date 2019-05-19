package com.vn.o2o.mapper;

import com.vn.o2o.entity.Area;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AreaMapper {
    @Select("select * from tb_area")
    List<Area> queryAllArea();
}
