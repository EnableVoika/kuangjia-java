package com.voika.myundefined.infrastructure.utils;

import java.util.regex.PatternSyntaxException;

public class StringUtil {

    public static boolean isEmpty(String var) {
        return null == var ? true : var.trim().length() == 0 ? true : "".equals(var) ? true : false;
    }

    public static boolean isNotEmpty(String var) {
        return !isEmpty(var);
    }

    public static boolean isAllEmpty(String... vars) {
        if (vars.length == 0) {
            return true;
        }
        for (String var : vars) {
            // 如果是空，下一个
            if (isEmpty(var)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean isAllNotEmpty(String... vars) {
        if (vars.length == 0) {
            return false;
        }
        for (String var : vars) {
            if (isEmpty(var)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnyEmpty(String... vars) {
        if (vars.length == 0) {
            return true;
        }
        for (String var : vars) {
            if (isEmpty(var)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 截取占位符拼成字符串
     */
    public static String separatorStr(String msg, String separator, String... param) {
        if (isAnyEmpty(msg, separator)) {
            return "";
        }
        if (isNotEmpty(msg) && (null == param || 0 == param.length)) {
            return "";
        }
        for (int i = 0; i < param.length; i++) {
            try {
                msg = msg.replaceFirst(separator, param[i]);
            } catch (PatternSyntaxException e) {
                throw new RuntimeException("\"" + separator + "\"" + "为特殊或者正则表达式,不能使用");
            }
        }
        try {
            msg = msg.replaceAll(separator, "");
        } catch (PatternSyntaxException e) {
            throw new RuntimeException("\"" + separator + "\"" + "为特殊或者正则表达式,不能使用");
        }
        return msg;
    }


}
