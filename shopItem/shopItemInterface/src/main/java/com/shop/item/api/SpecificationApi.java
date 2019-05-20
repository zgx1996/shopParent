package com.shop.item.api;

import com.shop.item.pojo.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:38
 */
public interface SpecificationApi {
    @GetMapping(value = "spec/params")
    List<SpecParam> querySpecParamList(@RequestParam(value = "gid",required = false) Long gid,
                                                              @RequestParam(value = "cid",required = false) Long cid,
                                                              @RequestParam(value = "searching",required = false) Boolean searching);
}
