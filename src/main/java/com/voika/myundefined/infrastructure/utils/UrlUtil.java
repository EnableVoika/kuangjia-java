package com.voika.myundefined.infrastructure.utils;

import java.util.Map;
import java.util.Set;

public class UrlUtil {

    public static String makeUrl(String baseUrl, Map<String,Object> param) {
        if (StringUtil.isEmpty(baseUrl)) {
            return "";
        }
        if (null == param || 0 == param.size()) {
            return baseUrl;
        }
        StringBuffer buffer = new StringBuffer(baseUrl).append("?");
        Set<String> keys = param.keySet();
        for (String key : keys) {
            Object o = param.get(key);
            buffer.append(key).append("=").append(o).append("&");
        }
        buffer.deleteCharAt(buffer.lastIndexOf("&"));
        return buffer.toString();
    }

}
