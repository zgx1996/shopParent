package com.shop.item.api;

import com.shop.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:19
 */
public interface BrandApi {

    @GetMapping("/brand/id")
    Brand queryBrandById(@RequestParam(name = "id") Long id);

}
