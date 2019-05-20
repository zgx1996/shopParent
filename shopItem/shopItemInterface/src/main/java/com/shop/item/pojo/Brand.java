package com.shop.item.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 15:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="tb_brand")
public class Brand {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String image;
    /**
     * 品牌首字母
     */
    private String letter;

}
