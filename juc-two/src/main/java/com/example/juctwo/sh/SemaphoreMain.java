package com.example.juctwo.sh;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * packageName com.example.juctwo.sh
 *
 * @author remaindertime
 * @className SemaporeMain
 * @date 2024/10/31
 * @description
 */
@Slf4j
public class SemaphoreMain {

    /**
     小明、小刚、小红、小绿在同一个公交站等同一路公交车。假设公交车只有3个座位，
     并且前一个站到后面一个站行驶所需要的平均时间为1s，写出他们4人在上车后占座的情况。
     4人上车后需要经历的站点数量分别为：
     ● 小明：2
     ● 小刚：4
     ● 小红：4
     ● 小绿：4
     */

    //自定义线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            4,
            4,
            5000,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque());
    //初始化信号量并设置令牌数量
    public static Semaphore semaphore =new Semaphore(3);
    //每一个站公交行驶时间 ms
    public static int TIME = 1000;
    public static void main(String[] args) {
        log.info("公交车到达站台----");
        executor.execute(new Seat("小明",2));
        executor.execute(new Seat("小刚", 4));
        executor.execute(new Seat("小红",4));
        executor.execute(new Seat("小绿", 4));
    }
}
