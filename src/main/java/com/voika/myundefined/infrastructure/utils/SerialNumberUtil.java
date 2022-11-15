package com.voika.myundefined.infrastructure.utils;

public class SerialNumberUtil {

    public static final String ALL_EN = "en";
    public static final String ALL_NUM = "num";
    public static final String EN_NUM = "em";

    public static String getSerialNumber(String prefix, int len) {
        StringBuffer var2 = new StringBuffer();
        int i = 0;
        if (len <= 0) {
            len = 10;
        }
        while (i < len) {
            int v = (int) (Math.random() * 10);
            var2.append(v);
            i++;
        }
        if (null == prefix || prefix.trim().equals("")) {
            return var2.toString();
        }
        StringBuffer var1 = new StringBuffer(prefix);
        return var1.append(var2).toString();
    }

    /**
     * 生成 前缀+10位 ，带有前缀的随机数
     * 总长度
     * @param prefix
     * @return
     */
    public static String getSerialNumber(String prefix) {
        return getSerialNumber(prefix, 10);
    }

    /**
     * 生成指定长度随机数
     * @param len 长度
     * @return
     */
    public static String getSerialNumber(int len) {
        StringBuffer var2 = new StringBuffer(len);
        int i = 0;
        if (len <= 0) {
            len = 10;
        }
        while (i < len) {
            int v = (int) (Math.random() * 10);
            var2.append(v);
            i++;
        }
        return var2.toString();
    }

    /**
     * 默认生成10随机数
     * @return
     */
    public static String getSerialNumber() {
        StringBuffer var2 = new StringBuffer();
        int len = 0;
        while (len < 10) {
            int v = (int) (Math.random() * 10);
            var2.append(v);
            len++;
        }
        return var2.toString();
    }
}
