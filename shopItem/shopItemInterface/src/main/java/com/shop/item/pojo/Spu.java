package com.shop.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 15:14
 */
@Data
@Table(name = "tb_spu")
public class Spu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private String title;
    private String subTitle;
    private Boolean saleable;
    private Boolean valid;
    private Date createTime;
    private Date lastUpdateTime;


}
