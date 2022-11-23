package com.voika.myundefined.infrastructure.utils;

import java.util.UUID;

public class BusinessUtil {

    private String bizId;

    /**
     * 生成业务主键
     * @return
     */
    public static String generatorBizId() {
        String replace = UUID.randomUUID().toString().replace("-", "");
        return replace;
    }

}
