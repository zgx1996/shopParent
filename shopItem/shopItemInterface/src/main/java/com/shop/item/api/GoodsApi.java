package com.shop.item.api;

import com.shop.common.vo.PageResult;
import com.shop.item.pojo.Sku;
import com.shop.item.vo.SpuVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:17
 */
public interface GoodsApi {
    @GetMapping(value = "/spu/detail/{spuId}")
    SpuVO detailSpu(@PathVariable(name = "spuId") Long spuId);

    @GetMapping(value = "/sku/list")
    List<Sku> querySkuListBySpuId(@RequestParam(name = "id") Long id);

    @GetMapping(value = "/spu/page")
    ResponseEntity<PageResult<SpuVO>> querySpuVOList(@RequestParam(value = "key", required = false) String key,
                                                     @RequestParam(value = "saleable", required = false) Boolean saleable,
                                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "rows", defaultValue = "5") Integer rows);
}
