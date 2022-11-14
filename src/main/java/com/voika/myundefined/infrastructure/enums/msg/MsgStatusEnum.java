package com.voika.myundefined.infrastructure.enums.msg;

public enum MsgStatusEnum {

    NEW_MSG(1,"新消息"),
    OLD_MSG(0,"已读消息"),
    ;

    private Integer code;
    private String value;
    MsgStatusEnum(Integer code,String value) {
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
