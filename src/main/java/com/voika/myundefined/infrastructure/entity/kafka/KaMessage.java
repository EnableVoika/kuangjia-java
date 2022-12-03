package com.voika.myundefined.infrastructure.entity.kafka;

import lombok.Data;

import java.io.Serializable;

@Data
public class KaMessage<T> implements Serializable {

    private String topicId;
    private T content;

}
