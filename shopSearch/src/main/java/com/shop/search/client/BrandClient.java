package com.shop.search.client;

import com.shop.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:19
 */
@FeignClient("item-service")
public interface BrandClient extends BrandApi{
}
