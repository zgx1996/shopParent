package com.shop.search.client;

import com.shop.common.vo.PageResult;
import com.shop.item.vo.SpuVO;
import com.shop.search.pojo.Goods;
import com.shop.search.repository.GoodsRepository;
import com.shop.search.service.IGoodsIndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 15:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private IGoodsIndexService goodsIndexService;


    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void createIndex(){
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
    }

    @Test
    public void loadData(){
        ResponseEntity<PageResult<SpuVO>> listResponseEntity = goodsClient.querySpuVOList(null, true, 1, 500);
        PageResult<SpuVO> body = listResponseEntity.getBody();
        List<SpuVO> spuVOList = body.getItems();
        System.out.println("nihao"+spuVOList);
        List<Goods> goodsList = spuVOList.stream().map(goodsIndexService::buildGoodsIndex).collect(Collectors.toList());
        goodsRepository.saveAll(goodsList);
    }

}
