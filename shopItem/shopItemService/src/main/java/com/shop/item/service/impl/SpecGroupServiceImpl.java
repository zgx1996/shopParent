package com.shop.item.service.impl;

import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.item.mapper.SpecGroupMapper;
import com.shop.item.pojo.SpecGroup;
import com.shop.item.service.ISpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 11:38
 */
@Service
public class SpecGroupServiceImpl implements ISpecGroupService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Override
    public List<SpecGroup> querySpecGroupList(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroupList = specGroupMapper.select(specGroup);
        if(Objects.isNull(specGroupList)){
            throw new BaseException(ExceptionEnum.NOT_FOUND_DATA);
        }
        return specGroupList;
    }

    @Override
    public SpecGroup saveSpecGroup(SpecGroup specGroup) {
        specGroup.setId(null);
        int count = specGroupMapper.insertSelective(specGroup);
        if(count != 1){
            throw new BaseException(ExceptionEnum.SAVE_FAIL);
        }
        return specGroup;
    }

    @Override
    public Integer deleteSpecGroup(Long id) {
        int i = specGroupMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new BaseException(ExceptionEnum.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public Integer upadteSpecGroup(SpecGroup specGroup) {
        int i = specGroupMapper.updateByPrimaryKeySelective(specGroup);
        if(i != 1){
            throw new BaseException(ExceptionEnum.SAVE_FAIL);
        }
        return i;
    }
}
