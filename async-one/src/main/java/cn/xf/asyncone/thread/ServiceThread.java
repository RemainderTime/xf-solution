package cn.xf.asyncone.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * packageName cn.xf.asyncone.thread
 *
 * @author remaindertime
 * @className ServiceThread
 * @date 2024/7/26
 * @description
 */
@Slf4j
public abstract class ServiceThread implements Runnable{

    protected Thread thread;
    protected volatile boolean stopped = false;
    protected boolean isDaemon = false;

    //Make it able to restart the thread
    private final AtomicBoolean started = new AtomicBoolean(false);

    public ServiceThread() {

    }

    public abstract String getServiceName();

    public void start() {
        log.info("Try to start service thread:{} started:{} lastThread:{}", getServiceName(), started.get(), thread);
        if (!started.compareAndSet(false, true)) {
            return;
        }
        stopped = false;
        this.thread = new Thread(this, getServiceName());
        this.thread.setDaemon(isDaemon);
        this.thread.start();
        log.info("Start service thread:{} started:{} lastThread:{}", getServiceName(), started.get(), thread);
    }
}
