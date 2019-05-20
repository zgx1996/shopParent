package com.shop.search.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.shop.common.utils.JsonUtils;
import com.shop.common.utils.NumberUtils;
import com.shop.item.pojo.*;
import com.shop.item.vo.SpuVO;
import com.shop.search.client.BrandClient;
import com.shop.search.client.CategoryClient;
import com.shop.search.client.GoodsClient;
import com.shop.search.client.SpecificationClient;
import com.shop.search.pojo.Goods;
import com.shop.search.repository.GoodsRepository;
import com.shop.search.service.IGoodsIndexService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/11 16:38
 */
@Service
public class GoodsIndexServiceImpl implements IGoodsIndexService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private BrandClient brandClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpecificationClient specificationClient;

    @Override
    public Goods buildGoodsIndex(Spu spu){
        List<Category> categoryList = categoryClient.queryCategoryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        List<String> nameList = categoryList.stream().map(Category::getName).collect(Collectors.toList());
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        String all = spu.getTitle() + StringUtils.join(nameList) + brand.getName();

        List<Sku> skuList = goodsClient.querySkuListBySpuId(spu.getId());
        List<HashMap<String, Object>> skus = skuList.stream().collect(new SkusCollector());
        Set<Long> priceSet = skuList.stream().map(Sku::getPrice).collect(Collectors.toSet());

        HashMap hashMap = new HashMap();
        List<SpecParam> specParamList = specificationClient.querySpecParamList(null, spu.getCid3(), true);
        SpuVO spuVO = goodsClient.detailSpu(spu.getId());
        SpuDetail spuDetail = spuVO.getSpuDetail();
        if(!Objects.isNull(spuDetail)){
            String genericSpec = spuDetail.getGenericSpec();
            String specialSpec = spuDetail.getSpecialSpec();
            Map<Long,String> genericList = JsonUtils.toMap(genericSpec,Long.class, String.class);
            Map<Long, List<String>> specialList = JsonUtils.nativeRead(specialSpec, new TypeReference<Map<Long, List<String>>>() {
            });
            specParamList.stream().forEach(item -> {
                String key = item.getName();
                Object value = null;
                if(item.getGeneric()){
                    value = genericList.get(item.getId());
                    value = getSegment((String) value,item);
                }else{
                    value = specialList.get(item.getId());
                }
                hashMap.put(key,value);
            });

            Goods goods = new Goods();

            goods.setBrandId(spu.getBrandId());
            goods.setCid1(spu.getCid1());
            goods.setCid2(spu.getCid2());
            goods.setCid3(spu.getCid3());
            goods.setCreateTime(new Date());
            goods.setId(spu.getId());
            goods.setSubTitle(spu.getSubTitle());

            goods.setAll(all);
            goods.setPrice(priceSet);
            goods.setSkus(JsonUtils.toString(skus));
            goods.setSpecs(hashMap);
            goodsRepository.save(goods);
            return goods;
        }
        return new Goods();



    }

    private String getSegment(String value, SpecParam specParam) {
        double v = NumberUtils.toDouble(value);
        String result = "其他";
        String segments = specParam.getSegments();
        if(StringUtils.isNotBlank(segments)){
            String[] split = segments.split(",");
            if(!Objects.isNull(split)){
                for(int i=0; i< split.length;i++){
                    String item = split[i];
                    Double end = Double.MAX_VALUE;
                    String[] split1 = item.split("-");
                    if(!Objects.isNull(split1)){
                        Double start = NumberUtils.toDouble(split1[0]);
                        if(split1.length == 2){
                            end = NumberUtils.toDouble(split1[1]);
                        }
                        if(v >= start && v < end){
                            if(split1.length == 1){
                                result = split1[0] + specParam.getUnit() + "以上";
                            }else if(start == 0){
                                result = split1[1] + specParam.getUnit() + "以下";
                            }else{
                                result = item + specParam.getUnit();
                            }
                        }
                        break;
                    }
                }
            }
        }
        return result;
    }

    class SkusCollector implements Collector<Sku,HashMap<String,Object>,List<HashMap<String,Object>>>{

        @Override
        public Supplier<HashMap<String, Object>> supplier() {
            return HashMap::new;
        }

        @Override
        public BiConsumer<HashMap<String, Object>, Sku> accumulator() {
            return (hashMap,sku)-> {
                hashMap.put("id",sku.getId());
                hashMap.put("title",sku.getTitle());
                hashMap.put("price",sku.getPrice());
                hashMap.put("image", StringUtils.substringBefore(sku.getImages(),","));
            };
        }

        @Override
        public BinaryOperator<HashMap<String, Object>> combiner() {
            return null;
        }

        @Override
        public Function<HashMap<String,Object>,List<HashMap<String,Object>>> finisher() {
            return map -> {
                ArrayList<HashMap<String,Object>> objects = new ArrayList<>();
                objects.add(map);
                return objects;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            return EnumSet.of(Collector.Characteristics.CONCURRENT, Characteristics.UNORDERED);
        }
    }


}
