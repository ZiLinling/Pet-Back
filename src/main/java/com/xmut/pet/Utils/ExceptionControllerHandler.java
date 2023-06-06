package com.xmut.pet.Utils;

import com.xmut.pet.Exception.tokenException;
import com.xmut.pet.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@CrossOrigin
public class ExceptionControllerHandler {

    @ExceptionHandler(value= tokenException.class)
    public Result errorHandlertoken(HttpServletRequest request, Exception ex ){
        Result result=new Result();
        result.againLogin("尚未登录或者token超时");
        return result;
    }
}
