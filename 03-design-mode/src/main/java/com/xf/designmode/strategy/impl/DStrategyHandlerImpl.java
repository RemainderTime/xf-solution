package com.xf.designmode.strategy.impl;

import com.xf.designmode.strategy.AbstractStrategyHandler;
import com.xf.designmode.strategy.Strategy;
import com.xf.designmode.strategy.StrategyEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * D策略处理类(使用自定义策略注解注册策略)
 */
@Strategy(StrategyEnums.D)
@Component
@Slf4j
public class DStrategyHandlerImpl extends AbstractStrategyHandler {

    @Override
    protected String doHandler(String param) {
        log.info("D策略处理------{}", param);
        return "D";
    }
}
