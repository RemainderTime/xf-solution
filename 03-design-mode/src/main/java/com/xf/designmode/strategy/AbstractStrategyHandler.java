package com.xf.designmode.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 抽象策略处理类
 */
@Slf4j
public abstract class AbstractStrategyHandler implements StrategyHandler{

    /**
     * 链路统一处理逻辑
     * @param param
     *
     */
    @Override
    public String handler(String param) {
        String s = doHandler(param);
        //后置处理方法
        this.afterCommonHandler();
        return s;
    }

    /**
     * 抽象方法，由子类实现
     * @param param
     * @return
     */
    protected abstract String doHandler(String param);


    /**
     * 后置统一处理逻辑
     *
     */
    private void afterCommonHandler() {
        log.info("后置统一处理策略逻辑--------");
    }
}
