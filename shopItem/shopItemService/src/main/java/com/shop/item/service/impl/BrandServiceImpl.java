package com.shop.item.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.common.vo.PageResult;
import com.shop.item.mapper.BrandMapper;
import com.shop.item.pojo.Brand;
import com.shop.item.service.IBrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 15:11
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandList(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        if(rows != -1){
            PageHelper.startPage(page,rows);
        }

        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name", "%" + key + "%");
        }
        if(!StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy + (desc ? "desc" : "asc"));
        }
        List<Brand> brands = brandMapper.selectByExample(example);
        System.out.println("brands的size：" + brands.size());
        if(Objects.isNull(brands)){
            throw new BaseException(ExceptionEnum.BRAND_NOT_FOUND);
        }
        if(rows != -1){
            PageInfo pageInfo = new PageInfo(brands);
            return new PageResult(pageInfo.getTotal(), brands);
        }else{
            return new PageResult((long) brands.size(), brands);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Brand saveBrand(Brand brand, List<String> cids) {
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if(count != 1){
            throw new BaseException(ExceptionEnum.BRAND_SAVE_FAIL);
        }
        cids.stream().forEach(categoryId -> {
            int count2 = brandMapper.insertCategoryBrand(categoryId,brand.getId());
            if(count2 != 1){
                throw new BaseException(ExceptionEnum.BRAND_SAVE_FAIL);
        }
        });
        return brand;
    }

    @Override
    public Brand queryBrandDetailById(Long brandId) {
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        if(Objects.isNull(brand)){
            throw new BaseException(ExceptionEnum.NOT_FOUND_DATA);
        }
        return brand;
    }

    @Override
    public List<Brand> queryBrandListByCid(String cid) {
        List<Brand> brandList = this.brandMapper.queryBrandListByCid(cid);
        if(Objects.isNull(brandList)){
            throw new BaseException(ExceptionEnum.NOT_FOUND_DATA);
        }
        return brandList;
    }
}
