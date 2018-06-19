package com.imooc.utils;

import java.util.Random;

/**
 * @Auther: vn
 * @Date: 2018/6/12 22:30
 * @Description:
 */
public class KeyUtil {
    public static synchronized String genUniqueKey() {
        Random random = new Random();

        Integer num = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(num);
    }

}
