package com.shop.common.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/6 16:28
 */
@NoArgsConstructor
@AllArgsConstructor
public enum  ExceptionEnum {

    //公共错误
    PARAMS_IS_INVALID(500,"请求参数不正确"),

    //商品分类
    CATEGORY_NOT_FOUND(500,"没有查询到对应的商品分类"),

    //商品品牌
    BRAND_NOT_FOUND(500,"没有查询到对应的品牌"),
    BRAND_SAVE_FAIL(500,"保存品牌失败"),

    NOT_FOUND_DATA(500,"没有查询到对应数据"),

    FILE_UPLOAD_FAIL(500, "文件上传失败"),
    FILE_TYPE_NOT_ALLOW(500,"文件类型不支持"),

    DELETE_FAIL(500,"删除失败"),
    SAVE_FAIL(500,"保存失败");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
