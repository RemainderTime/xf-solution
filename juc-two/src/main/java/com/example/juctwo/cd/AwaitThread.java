package com.example.juctwo.cd;

import lombok.extern.slf4j.Slf4j;

/**
 * packageName com.example.juctwo.cd
 *
 * @author remaindertime
 * @className AwaitThread
 * @date 2024/10/31
 * @description
 */
@Slf4j
public class AwaitThread implements Runnable{
    private String name;
    public AwaitThread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        ConditionMain.LOCK.lock();
        try {
            log.info("线程{}开始等待-----", name);
            ConditionMain.condition.await();
            log.info("线程{}被唤醒-----", name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            ConditionMain.LOCK.unlock();
        }
    }
}
