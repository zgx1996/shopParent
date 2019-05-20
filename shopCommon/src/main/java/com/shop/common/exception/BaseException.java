package com.shop.common.exception;

import com.shop.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 16:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
