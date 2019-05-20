package com.shop.item.service;

import com.shop.item.pojo.SpecParam;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 14:00
 */
public interface ISpecParamService {
    SpecParam saveSpecParam(SpecParam specParam);

    List<SpecParam> querySpecParamList(Long gid, Long cid, Boolean searching);

    int deleteSpecParam(Long id);

    void upadteSpecParam(SpecParam specParam);
}
