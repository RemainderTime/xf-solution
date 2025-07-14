package com.xf.designmode.strategy;

import java.lang.annotation.*;

/**
 * 策略注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Strategy {
    StrategyEnums value();
}