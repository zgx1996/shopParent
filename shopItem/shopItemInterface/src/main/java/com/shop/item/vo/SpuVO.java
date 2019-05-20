package com.shop.item.vo;

import com.shop.item.pojo.Sku;
import com.shop.item.pojo.Spu;
import com.shop.item.pojo.SpuDetail;
import lombok.Data;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 15:25
 */
@Data
public class SpuVO extends Spu{

    /**
     * 全目录名称
     */
    private String cname;
    /**
     * 品牌名称
     */
    private String bname;

    private List<Sku> skus;

    private SpuDetail spuDetail;

}
