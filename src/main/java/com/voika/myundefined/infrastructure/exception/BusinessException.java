package com.voika.myundefined.infrastructure.exception;

public class BusinessException extends RuntimeException {

    private String msg;
    private Integer code;

    public BusinessException() {
        super();
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
