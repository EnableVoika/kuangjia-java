package com.voika.myundefined.infrastructure.utils;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    /**
     * string转list
     *
     * @param str
     * @param separator
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

    public static List<String> str2list(String str) {
        if (StringUtil.isEmpty(str)) {
            return new ArrayList<>();
        }
        return str2list(str, ",");
    }

    /**
     * list转str
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

    public static String list2str(List<String> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            return list2str(list, ",");
        }
        return null;
    }

}
