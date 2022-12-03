package com.voika.myundefined.infrastructure.entity.kafka;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message<T> implements Serializable {

    private String id;
    private T content;

}
