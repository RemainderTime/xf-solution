package com.xf.designmode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * 本案例实现的是一种典型的、结构清晰的 策略+工厂+模板方法 的复合设计模式应用结构，
     * 适用于需要根据不同条件动态切换处理逻辑的场景,并实现自动注册处理逻辑的工厂（自定义策略注解）
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
