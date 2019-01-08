package com.vn.shop.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DynamicDataSourceHolder {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    public static String getDbType() {
        String db = contextHolder.get();
        if (null == db) {
            return DB_MASTER;
        }
        return db;
    }

    public static void setDbType(String str) {
        LOGGER.debug("所使用的数据源为：{}", str);
        contextHolder.set(str);
    }

    public static void cleanDbType() {
        contextHolder.remove();
    }
}
