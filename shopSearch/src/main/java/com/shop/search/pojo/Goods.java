package com.shop.search.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 12:10
 */
@Data
@Document(indexName = "goods", type = "docs", shards = 1, replicas = 1)
public class Goods {

    @Id
    private Long id;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String all;
    @Field(type = FieldType.Keyword , index = false)
    private String subTitle;
    private Long brandId;
    private Long cid1;
    private Long cid2;
    private Long cid3;
    private Date createTime;
    private Set<Long> price;
    @Field(type = FieldType.Keyword, index = false)
    private String skus;
    private Map<String , Object> specs;


}
