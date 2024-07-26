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
 * @className ShipService
 * @date 2024/7/26
 * @description 轮船制造类
 */
@Service
@Slf4j
public class ShipService extends ServiceThread implements FactoryService {

    public static ConcurrentMap<String, ShipRequest> requestTable =
            new ConcurrentHashMap<>();
    public static PriorityBlockingQueue<ShipRequest> requestQueue =
            new PriorityBlockingQueue<>();

    public static class ShipRequest implements Comparable<ShipRequest> {

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
        public int compareTo(ShipRequest o) {
            return 0;
        }
    }

    @Override
    public String getServiceName() {
        return ShipService.class.getSimpleName();
    }

    @Override
    public void run() {
        log.info("轮船工厂启动-----------");
        while (this.factoryOfManufacture()) ;
    }

    @Override
    public boolean factoryOfManufacture() {
        boolean isSuccess = false;
        ShipRequest shipRequest = null;
        try {
            shipRequest = requestQueue.take();
            log.info("开始制造轮船-----------");
            //校验数据是否合法
            ShipRequest expectedRequest = this.requestTable.get(shipRequest.getUserId());
            if (null == expectedRequest) {
                log.warn("this mmap request expired, maybe cause timeout " + shipRequest.getUserId());
                return true;
            }
            if (expectedRequest != shipRequest) {
                log.warn("never expected here,  maybe cause timeout " + shipRequest.getUserId());
                return true;
            }
            //...业务处理
            Thread.sleep(3000);
            //...
            isSuccess = true;
        } catch (InterruptedException e) {
            log.warn(this.getServiceName() + " interrupted, possibly by shutdown.");
            return false;
        } finally {
            if (shipRequest != null && isSuccess) {
                log.info("轮船制造完成啦-----------");
                shipRequest.getCountDownLatch().countDown();
            }
        }
        return true;
    }

}
