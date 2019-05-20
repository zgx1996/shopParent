package com.shop.search.web;

import com.google.common.collect.Lists;
import com.shop.search.pojo.Goods;
import com.shop.search.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/12 13:24
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsIndexController {
    @Autowired
    GoodsRepository goodsRepository;

    @RequestMapping("/add")
    public Goods add(Goods blog) {
        return goodsRepository.save(blog);
    }

    @GetMapping
    public List<Goods> findAll(){
        Iterable<Goods> elements = goodsRepository.findAll();
        ArrayList<Goods> list = Lists.newArrayList(elements);
        return list;
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable(name="id") Long id) {
        goodsRepository.deleteById(id);
        return "success";
    }
}

