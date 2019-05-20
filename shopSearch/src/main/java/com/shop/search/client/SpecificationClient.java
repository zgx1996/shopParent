package com.shop.search.client;

import com.shop.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:37
 */
@FeignClient("item-service")
public interface SpecificationClient extends SpecificationApi{
}
