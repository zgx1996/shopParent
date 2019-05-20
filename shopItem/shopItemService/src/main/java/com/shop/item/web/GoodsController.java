package com.shop.item.web;

import com.shop.common.vo.PageResult;
import com.shop.item.pojo.Sku;
import com.shop.item.service.IGoodsService;
import com.shop.item.vo.SpuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 15:21
 */
@RestController
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping(value = "/spu/page")
    public ResponseEntity<PageResult<SpuVO>> querySpuVOList(@RequestParam(value = "key",required = false) String key,
                                                            @RequestParam(value = "saleable",required = false) Boolean saleable,
                                                            @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                            @RequestParam(value = "rows",defaultValue = "5") Integer rows){
        PageResult pageResult = goodsService.querySpuVOList(key,saleable,page,rows);
        return ResponseEntity.ok(pageResult);
    }

    @PostMapping(value = "/goods")
    public ResponseEntity saveGoods(@RequestBody SpuVO spuVO){
        goodsService.saveGoods(spuVO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping(value = "/spu/detail/{spuId}")
    public ResponseEntity<SpuVO> detailSpu(@PathVariable(name = "spuId") Long spuId){
        SpuVO spuVO = goodsService.detailSpu(spuId);
        return ResponseEntity.ok(spuVO);
    }

    @GetMapping(value = "/sku/list")
    public ResponseEntity querySkuListBySpuId(@RequestParam(name = "id") Long id){
        List<Sku> skuList = goodsService.querySkuListBySpuId(id);
        return ResponseEntity.ok(skuList);
    }

    @PutMapping(value = "/goods")
    public ResponseEntity updateGoods(@RequestBody SpuVO spuVO){
        goodsService.updateGoods(spuVO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
