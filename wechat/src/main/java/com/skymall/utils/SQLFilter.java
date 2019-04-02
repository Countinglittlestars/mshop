package com.skymall.utils;

import com.skymall.exception.ApiRRException;
import org.apache.commons.lang.StringUtils;

public class SQLFilter {
    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) {
        if (org.apache.commons.lang.StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = org.apache.commons.lang.StringUtils.replace(str, "'", "");
        str = org.apache.commons.lang.StringUtils.replace(str, "\"", "");
        str = org.apache.commons.lang.StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new ApiRRException("包含非法字符");
            }
        }

        return str;
    }
}
