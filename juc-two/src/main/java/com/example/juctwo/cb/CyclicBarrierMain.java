package com.example.juctwo.cb;

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
public class CyclicBarrierMain {
    /**
     * 小明、小刚和小红一起参加成都马拉松（设定全程为30公里）。他们配速不同，因此每人跑完10公里的时间各异。他们约定每跑完10公里都要等彼此到齐后再一起合照打卡，
     * 随后继续比赛，直到抵达终点拍照打卡后各自开车回家。
     * 他们每跑完10公里分别用时：
     * ● 小明：1s
     * ● 小刚：2s
     * ● 小红：3s
     */
    //自定义线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            5000,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque());
    //初始化线程编排对象
    public static CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("合照打卡~~~");
        }
    });

    public static void main(String[] args) {
        log.info("马拉松正式开始---");
        try {
            executor.execute(new Running("小明", 1000));
            executor.execute(new Running("小刚", 2000));
            executor.execute(new Running("小红", 3000));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
