package com.shop.item.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 21:31
 */
@Data
@Table(name = "tb_sku")
public class Sku {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String ownSpec;
    private String indexes;
    private Boolean enable;
    private Date createTime;
    private Date lastUpdateTime;

    @Transient
    private Integer stock;

}
