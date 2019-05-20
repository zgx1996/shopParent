package com.shop.search.client;

import com.shop.item.pojo.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 14:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClientTest {

    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void queryCategoryByIds() throws Exception {
        List<Category> categoryList = categoryClient.queryCategoryByIds(Arrays.asList(1L, 2L, 3L));
        Assert.assertEquals(3,categoryList.size());
        categoryList.stream().forEach(System.out::println);

    }

}