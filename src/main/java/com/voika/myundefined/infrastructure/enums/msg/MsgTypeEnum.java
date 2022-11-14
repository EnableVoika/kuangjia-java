package com.voika.myundefined.infrastructure.enums.msg;

public enum MsgTypeEnum {

    USER_REGISTRER(1,"用户注册申请")
    ;

    private Integer code;
    private String value;
    MsgTypeEnum(Integer code,String value) {
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
