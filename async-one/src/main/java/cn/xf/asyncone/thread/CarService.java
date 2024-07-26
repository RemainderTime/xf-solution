package cn.xf.asyncone.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * packageName cn.xf.asyncone.service
 *
 * @author remaindertime
 * @className CarService
 * @date 2024/7/26
 * @description 汽车制造类
 */
@Service
@Slf4j
public class CarService extends ServiceThread implements FactoryService {

    public static ConcurrentMap<String, CarRequest> requestTable =
            new ConcurrentHashMap<>();
    public static PriorityBlockingQueue<CarRequest> requestQueue =
            new PriorityBlockingQueue<>();

    public static class CarRequest implements Comparable<CarRequest> {

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
        public int compareTo(CarRequest o) {
            return 0;
        }
    }

    @Override
    public String getServiceName() {
        return CarService.class.getSimpleName();
    }

    @Override
    public void run() {
        log.info("汽车工厂启动-----------");
        while (this.factoryOfManufacture());
    }

    @Override
    public boolean factoryOfManufacture() {
        boolean isSuccess = false;
        CarRequest carRequest = null;
        try {
            carRequest = requestQueue.take();
            log.info("开始汽车制造-----------");
            //校验数据是否合法
            CarRequest expectedRequest = this.requestTable.get(carRequest.getUserId());
            if (null == expectedRequest) {
                log.warn("this mmap request expired, maybe cause timeout " + carRequest.getUserId());
                return true;
            }
            if (expectedRequest != carRequest) {
                log.warn("never expected here,  maybe cause timeout " + carRequest.getUserId());
                return true;
            }
            //...业务处理
            Thread.sleep(2000);
            //...
            isSuccess = true;
        } catch (InterruptedException e) {
            log.warn(this.getServiceName() + " interrupted, possibly by shutdown.");
            return false;
        } finally {
            if (carRequest != null && isSuccess) {
                log.info("汽车制造完成啦-----------");
                carRequest.getCountDownLatch().countDown();
            }
        }
        return true;
    }
}
