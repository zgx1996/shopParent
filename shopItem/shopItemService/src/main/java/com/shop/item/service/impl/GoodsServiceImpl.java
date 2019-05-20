package com.shop.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.common.vo.PageResult;
import com.shop.item.mapper.SkuMapper;
import com.shop.item.mapper.SpuDetailMapper;
import com.shop.item.mapper.SpuMapper;
import com.shop.item.mapper.StockMapper;
import com.shop.item.pojo.*;
import com.shop.item.service.IBrandService;
import com.shop.item.service.ICategoryService;
import com.shop.item.service.IGoodsService;
import com.shop.item.vo.SpuVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 15:21
 */
@Service
@Slf4j
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBrandService brandService;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public PageResult querySpuVOList(String key, Boolean saleable, Integer page, Integer rows) {
        //查询spu列表
        if(rows != -1){
            PageHelper.startPage(page,rows);
        }
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%" + key + "%");
        }
        if(saleable != null){
            criteria.andEqualTo("saleable",saleable);
        }
        example.setOrderByClause("last_update_time desc");
        List<Spu> spuList = spuMapper.selectByExample(example);
        PageInfo pageInfo = null;
        if(rows != -1){
            pageInfo = new PageInfo(spuList);
        }
        System.out.println("spuList的size：" + spuList.size());
        if(Objects.isNull(spuList)){
           throw new BaseException(ExceptionEnum.NOT_FOUND_DATA);
        }
        List<SpuVO> spuVOList = new ArrayList<>();
        for(int i=0;i<spuList.size();i++){
            SpuVO spuVO = new SpuVO();
            BeanUtils.copyProperties(spuList.get(i),spuVO);
            spuVOList.add(spuVO);
        }
        loadSpuVOCname(spuVOList);
        loadSpuVOBname(spuVOList);
        if(rows != -1){
            pageInfo = new PageInfo(spuList);
            PageResult pageResult = new PageResult(pageInfo.getTotal(),spuVOList);
            return pageResult;
        }else{
            PageResult pageResult = new PageResult((long) spuVOList.size(),spuVOList);
            return pageResult;
        }

    }


    @Override
    @Transactional
    public void saveGoods(SpuVO spuVO) {
        //save spu
        Spu spu = new Spu();
        spuVO.setId(null);
        BeanUtils.copyProperties(spuVO,spu);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setValid(false);
        spu.setSaleable(true);
        int spuCount = spuMapper.insert(spu);
        if(spuCount != 1){
            throw new BaseException(ExceptionEnum.SAVE_FAIL);
        }
        //save spuDetail
        SpuDetail spuDetail = spuVO.getSpuDetail();
        if(!Objects.isNull(spuDetail)){
            spuDetail.setSpuId(spu.getId());
            int spuDetailCount = spuDetailMapper.insert(spuDetail);
            if(spuDetailCount != 1){
                throw new BaseException(ExceptionEnum.SAVE_FAIL);
            }
        }
        //save sku and stock
        saveSkuAndStock(spuVO,spu);
    }

    private void saveSkuAndStock(SpuVO spuVO, Spu spu) {
        List<Sku> skus = spuVO.getSkus();
        if(CollectionUtils.isNotEmpty(skus)){
            skus.stream().forEach(item -> {
                item.setSpuId(spu.getId());
                item.setCreateTime(new Date());
                item.setEnable(true);
                item.setLastUpdateTime(item.getCreateTime());
                int skuCount = skuMapper.insert(item);
                if(skuCount != 1){
                    throw new BaseException(ExceptionEnum.SAVE_FAIL);
                }
                //save stock
                Stock stock = new Stock();
                stock.setSkuId(item.getId());
                stock.setStock(item.getStock());
                int stockCount = stockMapper.insert(stock);
                if(stockCount != 1){
                    throw new BaseException(ExceptionEnum.SAVE_FAIL);
                }
            });
        }
    }

    @Override
    public SpuVO detailSpu(Long spuId) {
        Spu spu = spuMapper.selectByPrimaryKey(spuId);
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
        SpuVO spuVO = new SpuVO();
        BeanUtils.copyProperties(spu,spuVO);
        spuVO.setSpuDetail(spuDetail);
        return spuVO;
    }

    @Override
    public List<Sku> querySkuListBySpuId(Long id) {
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> skuList = skuMapper.select(sku);
        if(Objects.isNull(skuList)){
           throw new BaseException(ExceptionEnum.NOT_FOUND_DATA);
        }
        return skuList;
    }



    @Override
    @Transactional(rollbackFor = BaseException.class)
    public void updateGoods(SpuVO spuVO) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuVO,spu);

        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        List<Sku> skuList = skuMapper.select(sku);
        if(CollectionUtils.isNotEmpty(skuList)){
            skuMapper.delete(sku);
            //删除库存
            List<Long> collect = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            stockMapper.deleteByIdList(collect);
        }
        //update spu
        spu.setLastUpdateTime(new Date());
        spu.setSaleable(null);
        spu.setValid(null);
        spu.setCreateTime(null);
        int spuCount = spuMapper.updateByPrimaryKeySelective(spu);
        if(spuCount != 1){
            throw new BaseException(ExceptionEnum.SAVE_FAIL);
        }

        //save spuDetail
        SpuDetail spuDetail = spuVO.getSpuDetail();
        if(!Objects.isNull(spuDetail)){
            spuDetail.setSpuId(spu.getId());
            int spuDetailCount = spuDetailMapper.updateByPrimaryKey(spuDetail);
            if(spuDetailCount != 1){
                throw new BaseException(ExceptionEnum.SAVE_FAIL);
            }
        }
        //save sku and stock
        saveSkuAndStock(spuVO,spu);
    }

    private void loadSpuVOBname(List<SpuVO> spuVOList) {
        spuVOList.forEach(item->{
            List<String> collect = categoryService.queryCategoryByIdList(Arrays.asList(item.getCid1(), item.getCid2(), item.getCid3())).stream().map(item1 -> item1.getName()).collect(Collectors.toList());
            item.setBname(StringUtils.join(collect,"/"));
        });
    }

    private void loadSpuVOCname(List<SpuVO> spuVOList) {
        spuVOList.forEach(item -> {
            Brand brand = brandService.queryBrandDetailById(item.getBrandId());
            item.setCname(brand.getName());
        });
    }




}
