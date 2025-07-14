package com.xf.designmode.strategy;


import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂类
 */
public class StrategyHandlerFactory {

    private static final Map<Integer, StrategyHandler> STRATEGY_MAP = new HashMap<>();

    //注册策略
    public static void registerStrategy(StrategyEnums strategyEnums, StrategyHandler strategyHandlerService) {
        try {
            STRATEGY_MAP.put(strategyEnums.getCode(), strategyHandlerService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取策略
    public static StrategyHandler getStrategy(Integer key) {
        StrategyHandler strategyHandler = STRATEGY_MAP.get(key);
        if (strategyHandler == null) {
            throw new IllegalArgumentException("No strategy found for type: " + key);
        }
        return strategyHandler;
    }
}
