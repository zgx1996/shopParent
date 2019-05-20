package com.shop.item.service.impl;

import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.item.mapper.SpecParamMapper;
import com.shop.item.pojo.SpecParam;
import com.shop.item.service.ISpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 14:01
 */
@Service
public class SpecParamServiceImpl implements ISpecParamService {

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public SpecParam saveSpecParam(SpecParam specParam) {
        specParam.setId(null);
        int i = specParamMapper.insertSelective(specParam);
        if(i != 1){
            throw new BaseException(ExceptionEnum.SAVE_FAIL);
        }
        return specParam;
    }

    @Override
    public List<SpecParam> querySpecParamList(Long gid, Long cid, Boolean searching) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        List<SpecParam> specParamList = specParamMapper.select(specParam);
        if(Objects.isNull(specParamList)){
            throw new BaseException(ExceptionEnum.NOT_FOUND_DATA);
        }
        return specParamList;
    }

    @Override
    public int deleteSpecParam(Long id) {
        int i = specParamMapper.deleteByPrimaryKey(id);
        if(i != 1){
            throw new BaseException(ExceptionEnum.DELETE_FAIL);
        }
        return i;
    }

    @Override
    public void upadteSpecParam(SpecParam specParam) {
        int i = specParamMapper.updateByPrimaryKeySelective(specParam);
        if(i != 1){
            throw new BaseException(ExceptionEnum.SAVE_FAIL);
        }
        return;
    }
}
