package com.vn.shop.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest httpServletRequest, String key) {
        try {
            return Integer.decode(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static Long getLong(HttpServletRequest httpServletRequest, String key) {
        try {
            return Long.valueOf(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest httpServletRequest, String key) {
        try {
            return Double.valueOf(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return -1d;
        }
    }

    public static Boolean getBoolean(HttpServletRequest httpServletRequest, String key) {
        try {
            return Boolean.valueOf(httpServletRequest.getParameter(key));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest httpServletRequest, String key) {
        try {
            String result = httpServletRequest.getParameter(key);
            if (null != result) {
                result = result.trim();
            }
            if ("".equals(result)) {
                result = null;
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }

}
