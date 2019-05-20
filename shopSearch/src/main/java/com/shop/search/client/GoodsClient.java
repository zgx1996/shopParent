package com.shop.search.client;

import com.shop.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:17
 */
@FeignClient(name="itemService",url = "http://127.0.0.1:8081")
public interface GoodsClient extends GoodsApi{

}
