package com.mexus.homeleisure.streaming.api.media.controller;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * 스트리밍 서비스에서 발생하는 Exception Handler
 *
 * @author always0ne
 * @version 1.0
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MediaExceptionHandler {

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public void handleIOError(IOException exception) throws IOException {
        if(!exception.getMessage().equals("현재 연결은 사용자의 호스트 시스템의 소프트웨어의 의해 중단되었습니다"))
            throw exception;
    }
}
