package com.shop.item.service;

import com.shop.common.vo.PageResult;
import com.shop.item.pojo.Brand;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 15:10
 */
public interface IBrandService {
    /**
     * 分页查询品牌列表
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    PageResult<Brand> queryBrandList(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    /**
     * 新增商品品牌
     * @param brand
     * @param cids
     * @return
     */
    Brand saveBrand(Brand brand, List<String> cids);

    Brand queryBrandDetailById(Long brandId);

    List<Brand> queryBrandListByCid(String cid);
}
