package com.xf.designmode.strategy.impl;

import com.xf.designmode.strategy.AbstractStrategyHandler;
import com.xf.designmode.strategy.Strategy;
import com.xf.designmode.strategy.StrategyEnums;
import com.xf.designmode.strategy.StrategyHandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * C策略处理类(使用自定义策略注解注册策略)
 */
@Strategy(StrategyEnums.C)
@Component
@Slf4j
public class CStrategyHandlerImpl extends AbstractStrategyHandler {

    @Override
    protected String doHandler(String param) {
        log.info("C策略处理------{}", param);
        return "C";
    }
}
