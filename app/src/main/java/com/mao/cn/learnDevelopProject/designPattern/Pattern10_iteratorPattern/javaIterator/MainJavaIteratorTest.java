package com.mao.cn.learnDevelopProject.designPattern.Pattern10_iteratorPattern.javaIterator;

/**
 * @author zhangkun
 * @time 2020-02-21 14:42
 */
public class MainJavaIteratorTest {
    public static void main(String[] args) {

        CakeHouseMenuJavaIt cakeHouseMenuIt = new CakeHouseMenuJavaIt();
        DinnerMenuJavaIt dinerMenuIt = new DinnerMenuJavaIt();

        CafeMenu cafeMenu = new CafeMenu();

        WaitressJavaIt  waitressIt = new WaitressJavaIt();
        waitressIt.addIterator(cakeHouseMenuIt.getIterator());
        waitressIt.addIterator(dinerMenuIt.getIterator());
        waitressIt.addIterator(cafeMenu.getIterator());
        waitressIt.printMenu();

    }
}
