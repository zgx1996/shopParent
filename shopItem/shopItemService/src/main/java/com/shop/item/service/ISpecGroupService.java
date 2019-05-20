package com.shop.item.service;

import com.shop.item.pojo.SpecGroup;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 11:38
 */
public interface ISpecGroupService {
    /**
     * 根据商品分类id查询属性组
     * @param cid
     * @return
     */
    List<SpecGroup> querySpecGroupList(Long cid);

    SpecGroup saveSpecGroup(SpecGroup specGroup);

    Integer deleteSpecGroup(Long id);

    Integer upadteSpecGroup(SpecGroup specGroup);

}
