package com.voika.myundefined.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class ControllerExceltionHandler {

    @ExceptionHandler(Exception.class)
    public JsonData noAuth(Exception e, HttpServletRequest request) {
        log.error("",e);
        return JsonData.error("系统异常,请联系管理员");
    }

}
