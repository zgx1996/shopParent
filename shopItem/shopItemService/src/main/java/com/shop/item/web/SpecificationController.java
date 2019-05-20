package com.shop.item.web;

import com.shop.item.pojo.SpecGroup;
import com.shop.item.pojo.SpecParam;
import com.shop.item.service.ISpecGroupService;
import com.shop.item.service.ISpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 11:39
 */
@RestController
@RequestMapping(value = "/spec")
public class SpecificationController {

    @Autowired
    private ISpecGroupService specGroupService;

    @Autowired
    private ISpecParamService specParamService;

    @GetMapping(value = "/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroupList(@PathVariable(value = "cid",required = true) Long cid){
        List<SpecGroup> list = specGroupService.querySpecGroupList(cid);
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/group")
    public ResponseEntity<SpecGroup> saveSpecGroup(@RequestBody SpecGroup specGroup){
        specGroup = specGroupService.saveSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(specGroup);
    }

    @DeleteMapping(value = "/group/{id}")
    public ResponseEntity<List<SpecGroup>> deleteSpecGroup(@PathVariable(value = "id",required = true) Long id){
        int count = specGroupService.deleteSpecGroup(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping(value = "/group")
    public ResponseEntity<SpecGroup> upadteSpecGroup(@RequestBody SpecGroup specGroup){
        specGroupService.upadteSpecGroup(specGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(specGroup);
    }


    @PostMapping(value = "/param")
    public ResponseEntity<SpecParam> saveSpecParam(@RequestBody SpecParam specParam){
        specParam = specParamService.saveSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).body(specParam);
    }


    @GetMapping(value = "/params")
    public ResponseEntity<List<SpecParam>> querySpecParamList(@RequestParam(value = "gid",required = false) Long gid,
                                                              @RequestParam(value = "cid",required = false) Long cid,
                                                              @RequestParam(value = "searching",required = false) Boolean searching){
        List<SpecParam> list = specParamService.querySpecParamList(gid,cid,searching);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping(value = "/param/{id}")
    public ResponseEntity<String> deleteSpecParam(@PathVariable(value = "id",required = true) Long id){
        int count = specParamService.deleteSpecParam(id);
        return ResponseEntity.ok().body("删除成功");
    }

    @PutMapping(value = "/param")
    public ResponseEntity<SpecParam> upadteSpecParam(@RequestBody SpecParam specParam){
        specParamService.upadteSpecParam(specParam);
        return ResponseEntity.status(HttpStatus.CREATED).body(specParam);
    }


}
