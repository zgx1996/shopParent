package com.shop.search.repository;

import com.shop.search.pojo.Goods;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 15:40
 */
@Configuration
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
