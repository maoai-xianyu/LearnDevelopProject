package com.mao.cn.learnDevelopProject.useDesign.d_10_circleCopyPattern.ooTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-27 23:52
 * @Description
 */
public class SplitService {

    /**
     * 拆分
     *
     * 这么写，不利于扩展，代码有点多
     *
     * @param box
     * @return
     */
    public static List<TruckCar> splitBox(IBox box) {
        List<TruckCar> truckCars = new ArrayList<>();
        while (box.getNumber() > 200) {
            // 进行拆分
            if (box instanceof PlasticClampBox) {
                PlasticClampBox orderBox = (PlasticClampBox) box;
                PlasticClampBox newBox = new PlasticClampBox();
                newBox.setName(orderBox.getName());
                newBox.setNumber(200);


                TruckCar truckCar = new TruckCar();
                truckCar.addBox(newBox);
                truckCars.add(truckCar);

                box.setNumber(box.getNumber() - 200);


            } else if (box instanceof CarPartBox) {
                CarPartBox orderBox = (CarPartBox) box;
                CarPartBox newBox = new CarPartBox();
                newBox.setName(orderBox.getName());
                newBox.setNumber(200);
                newBox.setCarBand(orderBox.getCarBand());


                TruckCar truckCar = new TruckCar();
                truckCar.addBox(newBox);
                truckCars.add(truckCar);

                box.setNumber(box.getNumber() - 200);

            }
        }

        TruckCar truckCar = new TruckCar();
        truckCar.addBox(box);
        truckCars.add(truckCar);


        return truckCars;
    }

}
