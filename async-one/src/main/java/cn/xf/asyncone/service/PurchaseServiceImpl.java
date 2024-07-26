package cn.xf.asyncone.service;

import cn.xf.asyncone.thread.AirPlaneService;
import cn.xf.asyncone.thread.CarService;
import cn.xf.asyncone.thread.ShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * packageName cn.xf.asyncone.service
 *
 * @author remaindertime
 * @className PurchaseServiceImpl
 * @date 2024/7/26
 * @description
 */
@Service
@Slf4j
public class PurchaseServiceImpl implements PurchaseService {

    private static int waitTimeOut = 1000 * 5;

    @Autowired
    private AirPlaneService airPlaneService;
    @Autowired
    private CarService carService;
    @Autowired
    private ShipService shipService;

    @Override
    public Boolean manufacturing(String userId) {

        long startTime = System.currentTimeMillis();
        //通知飞机厂计算飞机制造费用
        AirPlaneService.AirplaneRequest airplaneRequest = new AirPlaneService.AirplaneRequest();
        airplaneRequest.setUserId(userId);
        airPlaneService.requestTable.put(userId, airplaneRequest);
        airPlaneService.requestQueue.offer(airplaneRequest);

        //通知汽车厂计算汽车制造费用
        CarService.CarRequest carRequest = new CarService.CarRequest();
        carRequest.setUserId(userId);
        carService.requestTable.put(userId, carRequest);
        carService.requestQueue.offer(carRequest);

        //通知轮船厂计算轮船制造费用
        ShipService.ShipRequest shipRequest = new ShipService.ShipRequest();
        shipRequest.setUserId(userId);
        shipService.requestTable.put(userId, shipRequest);
        shipService.requestQueue.offer(shipRequest);


        //获取飞机制造
        AirPlaneService.AirplaneRequest airplaneOK = airPlaneService.requestTable.get(userId);
        //获取飞机制造
        CarService.CarRequest carOK = carService.requestTable.get(userId);
        //获取轮船制造
        ShipService.ShipRequest shipOK = shipService.requestTable.get(userId);
        try {
            //等待获取制造结果
            if (airplaneOK != null && carOK!= null && shipOK!= null) {
                boolean waitOK = airplaneOK.getCountDownLatch().await(waitTimeOut, TimeUnit.MILLISECONDS);
                boolean waitOK2 = carOK.getCountDownLatch().await(waitTimeOut, TimeUnit.MILLISECONDS);
                boolean waitOK3 = shipOK.getCountDownLatch().await(waitTimeOut, TimeUnit.MILLISECONDS);
                //如果都成功了，返回true
                if (waitOK && waitOK2 && waitOK3) {
                    log.info("总共用时："+(System.currentTimeMillis() - startTime));
                    airPlaneService.requestTable.remove(userId);
                    carService.requestTable.remove(userId);
                    shipService.requestTable.remove(userId);
                    return true;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        System.out.println("失败了总共用时："+(System.currentTimeMillis() - startTime));
        return false;
    }
}
