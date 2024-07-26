package cn.xf.asyncone;

import cn.xf.asyncone.thread.AirPlaneService;
import cn.xf.asyncone.thread.CarService;
import cn.xf.asyncone.thread.ShipService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsyncOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncOneApplication.class, args);
        //启动飞机制造线程
        AirPlaneService airPlaneService = new AirPlaneService();
        airPlaneService.start();
        //启动汽车制造线程
        CarService carService = new CarService();
        carService.start();
        //启动轮船制造线程
        ShipService shipService = new ShipService();
        shipService.start();
    }

}
