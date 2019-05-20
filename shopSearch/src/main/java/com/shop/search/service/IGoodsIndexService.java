package com.shop.search.service;

import com.shop.item.pojo.Spu;
import com.shop.search.pojo.Goods;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 16:38
 */
public interface IGoodsIndexService {
    Goods buildGoodsIndex(Spu spu);
}
