package com.example.juctwo.cdl;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * packageName com.example.juctwo.cld
 *
 * @author remaindertime
 * @className CountLatchDownMain
 * @date 2024/10/30
 * @description
 */
@Slf4j
public class CountDownLatchMain {

    /**
     * 在这个马拉松比赛的场景中，假设小明、小刚和小红参加成都马拉松。由于他们的配速不同，所以每个人跑完全程的完成时间也不同，但他们约好必须等到所有人到达终点后一起坐车回家 。
     * 他们跑完全程分别用时：
     * ● 小明：5s
     * ● 小刚：6s
     * ● 小红：7s
     */
    //自定义线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            5000,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque());
    //初始化线程编排对象
    public static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) {
        log.info("马拉松正式开始---");
        try {
            executor.execute(new Running("小明", 5000));
            executor.execute(new Running("小刚", 6000));
            executor.execute(new Running("小红", 7000));
            log.info("等待所有人到达终点---");
            //阻塞等待全部线程完成
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("全部到达终点---");
        log.info("一起坐车回家了");
    }
}
