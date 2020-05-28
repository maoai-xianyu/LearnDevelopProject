package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.circleCopy;

import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-27 23:50
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        CarPartBox carPartBox = new CarPartBox();

        carPartBox.setCarBand("大众");
        carPartBox.setName("汽车灯罩");
        carPartBox.setNumber(500);

        // 拆分
        List<TruckCar> carList = SplitService.splitBox(carPartBox);
        for (TruckCar truckCar : carList) {
            System.out.println("数量 "+truckCar.remove().getNumber());

        }
    }
}
