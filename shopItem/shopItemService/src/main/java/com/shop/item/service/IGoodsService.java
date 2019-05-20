package com.shop.item.service;

import com.shop.common.vo.PageResult;
import com.shop.item.pojo.Sku;
import com.shop.item.vo.SpuVO;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 15:21
 */
public interface IGoodsService {
    PageResult querySpuVOList(String key, Boolean saleable, Integer page, Integer rows);

    void saveGoods(SpuVO spuVO);

    SpuVO detailSpu(Long spuId);

    List<Sku> querySkuListBySpuId(Long id);

    void updateGoods(SpuVO spuVO);
}
