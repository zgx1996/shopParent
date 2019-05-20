package com.shop.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 21:34
 */
@Data
@Table(name = "tb_stock")
public class Stock {

    @Id
    private Long skuId;
    private Integer seckillStock;
    private Integer seckillTotal;
    private Integer stock;

}
