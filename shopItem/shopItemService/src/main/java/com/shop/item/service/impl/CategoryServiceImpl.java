package com.shop.item.service.impl;

import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.item.mapper.CategoryMapper;
import com.shop.item.pojo.Category;
import com.shop.item.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 22:15
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryListByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        if(CollectionUtils.isEmpty(categoryList)){
            throw new BaseException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }


    @Override
    public List<Category> queryCategoryByIdList(List<Long> idList){
        List<Category> categoryList = categoryMapper.selectByIdList(idList);
        return categoryList;
    }

}
