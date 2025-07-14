package com.xf.designmode.strategy;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 策略注解处理器
 */
@Component
public class StrategyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName){
        Class<?> clazz = bean.getClass();
        if (clazz.isAnnotationPresent(Strategy.class)) {
            StrategyEnums strategyEnum = clazz.getAnnotation(Strategy.class).value();
            StrategyHandlerFactory.registerStrategy(strategyEnum, (StrategyHandler) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }
}
