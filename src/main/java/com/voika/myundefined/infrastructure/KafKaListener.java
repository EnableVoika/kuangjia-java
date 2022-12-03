package com.voika.myundefined.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component("kafKaListener")
public class KafKaListener {

//    @KafkaListener(topics = "quickstart-events")
    @KafkaListener(topics = "test1")
    public void onMessage(String message){
        // 消费消息
        // 这里为插入数据库代码
        //insertIntoDb(buffer);
        log.info("接收到消息：" + message);
    }

}
