package com.shop.common.vo;

import com.shop.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 16:35
 */
@Data
public class ExceptionResult{

    private int status;
    private String msg;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum){
        this.status = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
        this.timestamp = System.currentTimeMillis();
    }

}
