package com.lingnuokeji.classmanagementsystemjava.exception;


import com.lingnuokeji.classmanagementsystemjava.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 全局异常处理器
*
* */
@Slf4j
@RestControllerAdvice
public class Global {
    /**
     * 全局异常处理
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/9/10 16:21
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result ex(Exception Bigex){
        log.info(String.valueOf(Bigex));
         return Result.error("对不起，操作失败请联系管理员");
    }
}
