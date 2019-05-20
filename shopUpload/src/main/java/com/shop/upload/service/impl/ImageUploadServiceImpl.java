package com.shop.upload.service.impl;

import com.shop.common.enums.ExceptionEnum;
import com.shop.common.exception.BaseException;
import com.shop.upload.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/9 10:00
 */
@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {

    private static final List ALLOW_FILE_TYPE = Arrays.asList("image/jpeg","image/jpg","image/png");

    @Override
    public String uploadImage(MultipartFile file) {
        String contentType = file.getContentType();
        if(!ALLOW_FILE_TYPE.contains(contentType)){
            throw new BaseException(ExceptionEnum.FILE_TYPE_NOT_ALLOW);
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(Objects.isNull(bufferedImage)){
                throw new BaseException(ExceptionEnum.FILE_UPLOAD_FAIL);
            }
        } catch (IOException e) {
            log.info("图片读取失败，{}",e.getMessage());
            e.printStackTrace();
        }

        String path = this.getClass().getClassLoader().getResource("").getPath();
        log.info("path:{}",path);
        File dest = new File("D:/" + file.getOriginalFilename());
        try {
            file.transferTo(dest);
            return "www.baidu.com";
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new BaseException(ExceptionEnum.FILE_UPLOAD_FAIL);
    }
}
