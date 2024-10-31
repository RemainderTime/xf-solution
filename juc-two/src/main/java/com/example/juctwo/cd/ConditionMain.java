package com.example.juctwo.cd;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * packageName com.example.juctwo.cd
 *
 * @author remaindertime
 * @className ConditionMain
 * @date 2024/10/31
 * @description
 */
@Slf4j
public class ConditionMain {
    /**
     简单使用：多个线程进入等待状态，最后的一个线程唤醒所有等待线程
     */
    //自定义线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            4,
            4,
            5000,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque());

    //定义ReentrantLock对象
    public final static ReentrantLock LOCK = new ReentrantLock();
    //获取并定义Condition对象
    public final static Condition condition = LOCK.newCondition();
    public static void main(String[] args) {
        executor.execute(new AwaitThread("线程1"));
        executor.execute(new AwaitThread("线程2"));
        executor.execute(new AwaitThread("线程3"));
        executor.execute(new SignalThread("线程4"));
    }
}