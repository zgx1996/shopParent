package com.shop.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 15:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResult<T> {

    private Long total;
    private List<T> items;


}
