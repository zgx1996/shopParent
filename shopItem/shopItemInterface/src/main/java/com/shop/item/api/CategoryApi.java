package com.shop.item.api;

import com.shop.item.pojo.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:05
 */
public interface CategoryApi {
    @GetMapping("category/list/ids")
    List<Category> queryCategoryByIds(@RequestParam(name = "ids") List<Long> ids);
}
