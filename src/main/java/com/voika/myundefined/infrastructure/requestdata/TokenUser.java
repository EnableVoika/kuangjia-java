package com.voika.myundefined.infrastructure.requestdata;

import lombok.Data;

import java.util.Date;

@Data
public class TokenUser {

    public TokenUser() {

    }

    private String userId;

    private Integer accountType;

    /**
     * 当前账号的状态 1=启用,0=禁用
     */
    private Integer status;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private Integer deleted;

    /**
     * 别名
     */
    private String alias;

    private Date createTime;

    private Date modifyTime;

}
