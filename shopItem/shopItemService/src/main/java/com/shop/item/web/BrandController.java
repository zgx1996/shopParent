package com.shop.item.web;

import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.common.vo.PageResult;
import com.shop.item.pojo.Brand;
import com.shop.item.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 14:29
 */
@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping(value = "/page")
    public ResponseEntity<PageResult<Brand>> queryBrandList(@RequestParam(name = "key", required = false) String key,
                                                            @RequestParam(name = "page", defaultValue = "1") Integer page,
                                                            @RequestParam(name = "rows", defaultValue = "5") Integer rows,
                                                            @RequestParam(name = "sortBy", required = false) String sortBy,
                                                            @RequestParam(name = "desc", defaultValue = "false") Boolean desc){
        PageResult<Brand> brandPageResult = brandService.queryBrandList(key, page, rows, sortBy, desc);
        return ResponseEntity.ok(brandPageResult);
    }

    @PostMapping
    public ResponseEntity saveBrand(Brand brand, @RequestParam(name = "cids",required = true) List<String> cids){
        if(Objects.isNull(cids)){
            throw new BaseException(ExceptionEnum.PARAMS_IS_INVALID);
        }
        Brand brandResult = brandService.saveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandResult);
    }

    @GetMapping(value = "/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable(name = "cid",required = true) String cid){
        List<Brand> brandList = brandService.queryBrandListByCid(cid);
        return ResponseEntity.status(HttpStatus.OK).body(brandList);
    }

    @GetMapping(value = "/id")
    public ResponseEntity<Brand> queryBrandById(@RequestParam(name = "id") Long id){
        Brand brand = brandService.queryBrandDetailById(id);
        return ResponseEntity.ok(brand);
    }

}
