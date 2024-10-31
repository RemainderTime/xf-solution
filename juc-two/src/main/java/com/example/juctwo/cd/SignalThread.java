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
public class SignalThread implements Runnable{
    private String name;
    public SignalThread(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        ConditionMain.LOCK.lock();
        try {
            log.info("线程{}开始唤醒其他线程-----", name);
            ConditionMain.condition.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConditionMain.LOCK.unlock();
        }
    }
}
