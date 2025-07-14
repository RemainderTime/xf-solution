package com.xf.designmode.strategy.impl;

import com.xf.designmode.strategy.AbstractStrategyHandler;
import com.xf.designmode.strategy.StrategyEnums;
import com.xf.designmode.strategy.StrategyHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * A策略处理类
 */
@Component
@Slf4j
public class AStrategyHandlerImpl extends AbstractStrategyHandler implements InitializingBean {

    @Override
    protected String doHandler(String param) {
        log.info("A策略处理------");
        return "A";
    }

    /**
     * 注册策略到工工厂类
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyHandlerFactory.registerStrategy(StrategyEnums.A, this);
    }
}
