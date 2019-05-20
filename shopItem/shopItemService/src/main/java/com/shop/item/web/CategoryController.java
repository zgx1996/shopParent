package com.shop.item.web;

import com.shop.item.pojo.Category;
import com.shop.item.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 22:16
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam(name = "pid") Long pid){
        return ResponseEntity.ok(categoryService.queryCategoryListByPid(pid));
    }
    @GetMapping("/list/ids")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam(name = "ids") List<Long> ids){
        List<Category> categoryList = categoryService.queryCategoryByIdList(ids);
        return ResponseEntity.ok(categoryList);
    }

}
