package com.voika.myundefined.infrastructure.entity.po;

import com.voika.myundefined.infrastructure.requestdata.RequestData;
import com.voika.myundefined.infrastructure.requestdata.TokenUser;
import com.voika.myundefined.infrastructure.utils.BusinessUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BasePO {

    private Long id;

    private String bizId;

    private Integer deleted;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private String creatorName;

    private String creatorId;

    private String operatorId;

    private String operatorName;

    private Long version;

    public <T> T create() {
        this.bizId = BusinessUtil.generatorBizId();
        this.deleted = 0;
        this.version = 0L;
        TokenUser tokenUser = RequestData.TokenUser;
        if (null != tokenUser) {
            this.creatorId = tokenUser.getUserId();
            this.creatorName = tokenUser.getUsername();
            this.operatorId = tokenUser.getUserId();
            this.operatorName = tokenUser.getUserId();
            this.createTime = LocalDateTime.now();
            this.modifyTime = LocalDateTime.now();
        }
        return (T)this;
    }
    public <T> T update() {
        TokenUser tokenUser = RequestData.TokenUser;
        if (null != tokenUser) {
            this.operatorId = tokenUser.getUserId();
            this.operatorName = tokenUser.getUserId();
            this.modifyTime = LocalDateTime.now();
        }
        return (T)this;
    }

}
