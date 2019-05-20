package com.shop.item.mapper;

import com.shop.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 15:15
 */
@org.apache.ibatis.annotations.Mapper
public interface BrandMapper extends Mapper<Brand> {
    @Insert("insert into tb_category_brand values (#{categoryId},#{brandId})")
    int insertCategoryBrand(@Param(value = "categoryId") String categoryId, @Param(value = "brandId") Long brandId);

    @Select("select * from tb_brand b left join tb_category_brand cb on cb.category_id = #{cid} and b.id = cb.brand_id ")
    List<Brand> queryBrandListByCid(String cid);
}
