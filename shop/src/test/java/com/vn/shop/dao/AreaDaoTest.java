package com.vn.shop.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vn.shop.BaseTest;
import com.vn.shop.entity.Area;

public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao AreaDao;

	@Test
	public void test() {
		List<Area> list = AreaDao.queryArea();
		assertEquals(2, list.size());
	}

}
