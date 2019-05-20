package com.shop.item.service;

import com.shop.item.pojo.Category;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 22:15
 */
public interface ICategoryService {
    /**
     * 根据parentId查询目录
     * @param pid
     * @return
     */
    List<Category> queryCategoryListByPid(Long pid);

    public List<Category> queryCategoryByIdList(List<Long> idList);
}
