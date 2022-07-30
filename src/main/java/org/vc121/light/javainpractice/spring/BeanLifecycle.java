package org.vc121.light.javainpractice.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Sam Lu
 * @date 2022/07/30
 */
public class BeanLifecycle implements ApplicationContextAware, BeanNameAware, InitializingBean {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware.setApplicationContext()");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware.setBeanName()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean.afterPropertiesSet()");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy");
    }

    public void initMethod() {
        System.out.println("initMethod()");
    }

    public void destroyMethod() {
        System.out.println("destroyMethod()");
    }

}
