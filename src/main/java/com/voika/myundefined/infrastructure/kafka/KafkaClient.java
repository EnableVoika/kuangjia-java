package com.voika.myundefined.infrastructure.kafka;

import com.voika.myundefined.infrastructure.entity.kafka.KaMessageDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("kafkaClient")
@Slf4j
public class KafkaClient{

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    public boolean sendMessage(String topic,String message) {
        // 发送消息
        try {
            kafkaTemplate.send(topic, message);
        }catch (Exception e) {
            log.error("kafka消息发送失败",e);
            return false;
        }
        return true;
    }

    public boolean sendMessage(KaMessageDO<String> message) {
        // 发送消息
        try {
            kafkaTemplate.send(message.getTopicId(),message.getContent());
        }catch (Exception e) {
            log.error("kafka消息发送失败",e);
            return false;
        }
        return true;
    }

}
