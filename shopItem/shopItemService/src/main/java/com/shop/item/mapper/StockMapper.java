package com.shop.item.mapper;

import com.shop.item.pojo.Stock;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 21:40
 */
@org.apache.ibatis.annotations.Mapper
public interface StockMapper extends Mapper<Stock>,DeleteByIdListMapper<Stock,Long> {
}
