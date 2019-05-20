package com.shop.search.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Arrays;

/**
 * @author person gxz
 * @version 1.0
 * @name zgx
 * @description
 * @date 2019/5/13 16:48
 */
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (containsBeanDefinition(beanFactory, "feignContext", "eurekaAutoServiceRegistration")) {
            BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
            bd.setDependsOn("eurekaAutoServiceRegistration");
        }
    }

    private boolean containsBeanDefinition(ConfigurableListableBeanFactory beanFactory, String... beans) {
        return Arrays.stream(beans).allMatch(b -> beanFactory.containsBeanDefinition(b));
    }
}

