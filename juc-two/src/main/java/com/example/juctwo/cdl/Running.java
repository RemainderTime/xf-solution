package com.example.juctwo.cdl;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName com.example.juctwo.cld
 *
 * @author remaindertime
 * @className Running
 * @date 2024/10/30
 * @description
 */
@Slf4j
public class Running implements Runnable {
    //名称
    private String name;
    //用时
    private int time;

    public Running(String name, int time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public void run() {
        log.info(name + "开始跑步了----");
        try {
            Thread.sleep(time);
            log.info(name + "达到终点了----用时：{}秒", time / 1000);
            //到达终点后计数器减1
            CountDownLatchMain.countDownLatch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
