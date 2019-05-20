package com.shop.item.mapper;

import com.shop.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 22:16
 */
@org.apache.ibatis.annotations.Mapper
public interface CategoryMapper extends Mapper<Category>,IdListMapper<Category,Long> {
}
