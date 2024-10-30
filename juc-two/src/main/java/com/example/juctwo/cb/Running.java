package com.example.juctwo.cb;

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
            log.info(name + "达到10公里了----用时：{}秒", time / 1000);
            //到达终点后计数器减1
            CyclicBarrierMain.barrier.await();
            Thread.sleep(time);
            log.info(name + "达到20公里了----用时：{}秒", time * 2 / 1000);
            CyclicBarrierMain.barrier.await();
            Thread.sleep(time);
            log.info(name + "达到终点30公里----用时：{}秒", time * 3 / 1000);
            CyclicBarrierMain.barrier.await();
            log.info(name + "独自开车回家了---");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
