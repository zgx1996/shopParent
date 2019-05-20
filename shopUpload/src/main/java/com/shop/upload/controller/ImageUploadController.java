package com.shop.upload.controller;

import com.shop.upload.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/8 20:02
 */
@RestController
@RequestMapping(value = "/upload")
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @PostMapping(value = "/image")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "file")MultipartFile file){
        String path = imageUploadService.uploadImage(file);
        return ResponseEntity.ok(path);
    }


}
