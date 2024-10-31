package com.example.juctwo.sh;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * packageName com.example.juctwo.sh
 *
 * @author remaindertime
 * @className Seat
 * @date 2024/10/31
 * @description
 */
@Slf4j
public class Seat implements Runnable {
    //用户名
    private String name;
    //需要经历的站点数量
    private Integer num;
    public Seat(String name, Integer num) {
        this.name = name;
        this.num = num;
    }
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        log.info(name + "上车了----");
        try {
            //获取令牌数量
            int tokenCount = SemaphoreMain.semaphore.availablePermits();
            if (tokenCount == 0) {
                log.info(name + "--等待--空缺座位----");
            }
            //获取令牌并设置超时时间
            boolean b = SemaphoreMain.semaphore.tryAcquire(SemaphoreMain.TIME * num, TimeUnit.MILLISECONDS);
            long awaitTime = System.currentTimeMillis();
            if (b) {
                log.info(name + "抢占到了座位----");
                if (tokenCount > 0) {
                    Thread.sleep(SemaphoreMain.TIME * num);
                } else {
                    //等待时间
                    long await = awaitTime - startTime;
                    Thread.sleep(SemaphoreMain.TIME * num - await);
                }
            } else {
                log.info(name + "整个行程没有抢到座位----");
            }
            long endTime = System.currentTimeMillis();
            log.info(name + "到站下车----等待用时：{}秒---占座时长：{}秒", (awaitTime - startTime) / 1000, Math.round((endTime - awaitTime) / 1000.0));
            //释放令牌
            SemaphoreMain.semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
