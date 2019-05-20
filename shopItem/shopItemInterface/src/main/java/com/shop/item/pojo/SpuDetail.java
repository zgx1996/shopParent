package com.shop.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 15:15
 */
@Data
@Table(name = "tb_spu_detail")
public class SpuDetail {

    @Id
    private Long spuId;
    private String description;
    private String specialSpec;
    private String genericSpec;
    private String packingList;
    private String afterService;


}
