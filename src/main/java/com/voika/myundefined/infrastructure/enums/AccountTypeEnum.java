package com.voika.myundefined.infrastructure.enums;

public enum AccountTypeEnum {

    ADMINISTRATOR(0,"超级管理员"),
    NORMAL(1,"普通用户")
    ;

    private Integer code;
    private String value;
    AccountTypeEnum(Integer code,String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return this.code;
    }
    public String getValue() {
        return this.value;
    }

}
