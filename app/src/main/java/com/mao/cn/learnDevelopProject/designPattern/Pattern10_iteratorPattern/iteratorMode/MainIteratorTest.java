package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.iteratorMode;

/**
 * @author zhangkun
 * @time 2020-02-21 14:42
 */
public class MainIteratorTest {
    public static void main(String[] args) {

        CakeHouseMenuIt cakeHouseMenuIt = new CakeHouseMenuIt();
        DinerMenuIt dinerMenuIt = new DinerMenuIt();

        WaitressIt waitressIt = new WaitressIt();
        waitressIt.addIterator(cakeHouseMenuIt.getIterator());
        waitressIt.addIterator(dinerMenuIt.getIterator());
        waitressIt.printMenu();

    }
}
