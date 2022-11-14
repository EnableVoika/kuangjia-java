package com.voika.myundefined.infrastructure.entity.dto.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailDTO {

    // 发件人
    private String fromUser;

    // 收件人
    private String toUser;

    // 邮件主题
    private String subject;

    // 邮件内容
    private String content;

}
