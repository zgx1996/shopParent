package com.shop.common.advice;

import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 16:26
 */
@ControllerAdvice
public class ExceptionHander {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResult> HandlerException(BaseException baseException){
        ExceptionEnum exceptionEnum = baseException.getExceptionEnum();
        ExceptionResult exceptionResult = new ExceptionResult(exceptionEnum);
        return ResponseEntity.status(exceptionEnum.getCode()).body(exceptionResult);
    }

}
