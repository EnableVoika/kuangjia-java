package com.voika.myundefined.infrastructure.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    /**
     * string转list
     * 例子: "1,2,3,4,5" 变成 [1,2,3,4,5]
     * @param str
     * @param separator 分隔符，以什么形式分割，例如：","
     * @return
     */
    public static List<String> str2list(String str, String separator) {
        if (StringUtil.isAnyEmpty(str, separator)) {
            return null;
        }
        String[] split = str.split(separator);
        List<String> data = Arrays.asList(split);
        return data;
    }

    /**
     * 例如 "1,2,3,4" 变成 [1,2,3,4]
     * 不传分隔符，默认逗号分割
     * @param str
     * @return
     */
    public static List<String> str2list(String str) {
        if (StringUtil.isEmpty(str)) {
            return new ArrayList<>();
        }
        return str2list(str, ",");
    }

    /**
     * list转str
     * 尽量不要用特殊字符，尤其是转义字符
     */
    public static String list2str(List<String> list, String separator) {
        if (StringUtil.isEmpty(separator)) {
            separator = ",";
        }
        if (CollectionUtil.isNotEmpty(list)) {
            StringBuffer buffer = new StringBuffer(list.size());
            for (String str : list) {
                buffer.append(str).append(separator);
            }
            buffer.deleteCharAt(buffer.length() - 1);
            return buffer.toString();
        }
        return null;
    }

    /**
     *
     * @param list
     * @return
     */
    public static String list2str(List<String> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            return list2str(list, ",");
        }
        return null;
    }

}
