package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
    @ExceptionHandler
    //用户名重复时的异常处理
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        //得到异常信息
        String mes=ex.getMessage();
        //判断是否是用户名重复
         if (mes.contains("Duplicate entry")){
            String[] split = mes.split(" ");
            String msg=split[2]+ MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }
         //其他 错误
         else{
         return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
}
