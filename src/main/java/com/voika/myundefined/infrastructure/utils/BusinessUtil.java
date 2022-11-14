package com.voika.myundefined.infrastructure.utils;

import java.util.UUID;

public class BusinessUtil {

    private String bizId;

    public static String generatorBizId() {
        String replace = UUID.randomUUID().toString().replace("-", "");
        return replace;
    }

}
