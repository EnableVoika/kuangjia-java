package com.voika.myundefined.infrastructure.enums;

public enum AccountStatusEnum {

    ENABLE(1,"启用"),
    DISABLE(0,"禁用"),
    NO_ACTIVATION(2,"未激活")
    ;

    private Integer code;
    private String value;
    AccountStatusEnum(Integer code,String value) {
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
