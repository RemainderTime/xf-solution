package com.xf.designmode.strategy.impl;

import com.xf.designmode.strategy.AbstractStrategyHandler;
import com.xf.designmode.strategy.StrategyEnums;
import com.xf.designmode.strategy.StrategyHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * B策略处理类
 */
@Component
@Slf4j
public class BStrategyHandlerImpl extends AbstractStrategyHandler implements InitializingBean {

    @Override
    protected String doHandler(String param) {
        log.info("B策略处理------");
        return "B";
    }

    /**
     * 注册到工厂类中
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyHandlerFactory.registerStrategy(StrategyEnums.B, this);
    }
}
