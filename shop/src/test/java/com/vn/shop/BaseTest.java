package com.vn.shop;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring和Junit整合，Junit启动时加载SpringIoc容器
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)

//告知Junit Spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {

}
