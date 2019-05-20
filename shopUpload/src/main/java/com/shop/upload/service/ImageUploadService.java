package com.shop.upload.service;

import org.springframework.web.multipart.MultipartFile; /**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 10:00
 */
public interface ImageUploadService {
    /**
     * 图片上传
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file);
}
