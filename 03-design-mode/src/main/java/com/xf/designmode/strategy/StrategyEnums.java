package com.xf.designmode.strategy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * 策略枚举
 */
@AllArgsConstructor
@Getter
public enum StrategyEnums{
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D");

    private Integer code;

    private String desc;

    public static StrategyEnums getByCode(Integer code) {
        for (StrategyEnums value : StrategyEnums.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public static String getDescByCode(Integer code) {
        return Optional.ofNullable(getByCode(code))
                .map(StrategyEnums::getDesc)
                .orElse(null);
    }
}
