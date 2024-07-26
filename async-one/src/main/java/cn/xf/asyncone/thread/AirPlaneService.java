package cn.xf.asyncone.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * packageName cn.xf.asyncone.service
 *
 * @author remaindertime
 * @className AirPlaneRequest
 * @date 2024/7/26
 * @description 飞机制造类
 */
@Service
@Slf4j
public class AirPlaneService extends ServiceThread implements FactoryService {
    public static ConcurrentMap<String, AirplaneRequest> requestTable =
            new ConcurrentHashMap<>();
    public static PriorityBlockingQueue<AirplaneRequest> requestQueue =
            new PriorityBlockingQueue<>();

    public static class AirplaneRequest implements Comparable<AirplaneRequest> {

        private CountDownLatch countDownLatch = new CountDownLatch(1);

        private String userId;

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }

        public void setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public int compareTo(AirplaneRequest o) {
            return 0;
        }
    }

    @Override
    public String getServiceName() {
        return AirPlaneService.class.getSimpleName();
    }

    @Override
    public void run() {
        log.info("飞机工厂启动-----------");
        //循环处理不同的亲戚
        while (this.factoryOfManufacture()) ;
    }

    public boolean factoryOfManufacture() {
        boolean isSuccess = false;
        AirplaneRequest airplaneRequest = null;
        try {
            //等待飞机制造请求
            airplaneRequest = requestQueue.take();
            log.info("开始飞机制造-----------");
            //校验数据是否合法
            AirplaneRequest expectedRequest = this.requestTable.get(airplaneRequest.getUserId());
            if (null == expectedRequest) {
                log.warn("this mmap request expired, maybe cause timeout " + airplaneRequest.getUserId());
                return true;
            }
            if (expectedRequest != airplaneRequest) {
                log.warn("never expected here,  maybe cause timeout " + airplaneRequest.getUserId());
                return true;
            }
            //...业务处理
            Thread.sleep(4000);
            //...
            isSuccess = true;
        } catch (InterruptedException e) {
            log.warn(this.getServiceName() + " interrupted, possibly by shutdown.");
            return false;
        } finally {
            if (airplaneRequest != null && isSuccess) {
                log.info("飞机制造完成啦-----------");
                airplaneRequest.getCountDownLatch().countDown();
            }
        }
        return true;
    }


}
