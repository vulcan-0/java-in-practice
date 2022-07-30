package org.vc121.light.javainpractice;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.vc121.light.javainpractice.spring.BeanLifecycle;

/**
 * @author Sam Lu
 * @date 2022/07/30
 */
@SpringBootApplication
public class Application implements BeanPostProcessor {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanLifecycle beanLifecycle() {
        return new BeanLifecycle();
    }

}
