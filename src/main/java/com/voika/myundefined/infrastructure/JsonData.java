package com.voika.myundefined.infrastructure;

import com.voika.myundefined.infrastructure.utils.StringUtil;
import lombok.Data;

@Data
public class JsonData {

    private Object data;
    private String msg;
    private int code;
    private boolean ref;

    public JsonData(int code, boolean ref, String msg, Object data) {
        this.code = code;
        this.ref = ref;
        this.msg = msg;
        this.data = data;
    }

    public static JsonData success() {
        return new JsonData(200, true, "成功", null);
    }

    public static JsonData success(String msg) {
        msg = StringUtil.isEmpty(msg) ? "成功" : msg;
        return new JsonData(200, true, msg, null);
    }

    public static JsonData success(String msg, Object data) {
        msg = StringUtil.isEmpty(msg) ? "成功" : msg;
        return new JsonData(200, true, msg, data);
    }

    public static JsonData success(Object data) {
        return new JsonData(200, true, "成功", data);
    }

    public static JsonData error() {
        return new JsonData(250, false, "失败", null);
    }

    public static JsonData error(String msg) {
        msg = StringUtil.isEmpty(msg) ? "失败" : msg;
        return new JsonData(250, false, msg, null);
    }

    public static JsonData error(String msg, Object data) {
        msg = StringUtil.isEmpty(msg) ? "失败" : msg;
        return new JsonData(250, false, msg, data);
    }

    public static JsonData error(Object data) {
        return new JsonData(250, false, "失败", data);
    }

    public static JsonData noAuth() {
        return new JsonData(5000, false, "没有权限", null);
    }

    public static JsonData noAuth(String msg) {
        msg = StringUtil.isEmpty(msg) ? "没有权限" : msg;
        return new JsonData(5000, false, msg, null);
    }

    public static JsonData noAccount() {
        return new JsonData(5001, false, "账户不存在", null);
    }

    public static JsonData noAccount(String msg) {
        msg = StringUtil.isEmpty(msg) ? "账户不存在" : msg;
        return new JsonData(5001, false, msg, null);
    }

    public static JsonData errorPwd() {
        return new JsonData(5002, false, "密码错误", null);
    }

    public static JsonData errorPwd(String msg) {
        msg = StringUtil.isEmpty(msg) ? "密码错误" : msg;
        return new JsonData(5002, false, msg, null);
    }

    public static JsonData accountDisable() {
        return new JsonData(5003, false, "该账号已被禁用,请联系管理员解除禁用", null);
    }

    public static JsonData accountDisable(String msg) {
        msg = StringUtil.isEmpty(msg) ? "该账号已被禁用,请联系管理员解除禁用" : msg;
        return new JsonData(5003, false, msg, null);
    }

    public static JsonData accountNoActive() {
        return new JsonData(5004, false, "该账号未激活，请等待或联系管理激活", null);
    }

    public static JsonData accountNoActive(String msg) {
        msg = StringUtil.isEmpty(msg) ? "该账号未激活，请等待或联系管理激活" : msg;
        return new JsonData(5004, false, msg, null);
    }

    public static JsonData tokenExpired() {
        return new JsonData(40001, false, "token已过期", null);
    }
    public static JsonData malformedToken() {
        return new JsonData(40002, false, "token被篡改", null);
    }
    public static JsonData tokenSignatureErr() {
        return new JsonData(40003, false, "token签名错误", null);
    }
    public static JsonData noToken() {
        return new JsonData(40004, false, "没有token", null);
    }
    public static JsonData parseErrToken() {
        return new JsonData(40005, false, "token解析失败", null);
    }

}
