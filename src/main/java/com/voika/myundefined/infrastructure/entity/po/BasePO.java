package com.voika.myundefined.infrastructure.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BasePO {

    private Long id;

    private String bizId;

    private Integer deleted;

    private Date createTime;

    private Date modifyTime;

    private String creatorName;

    private String creatorId;

    private String operatorId;

    private String operatorName;

    private Long version;

}
