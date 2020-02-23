package com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode.CakeHouseMenuC;
import com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode.DinnerMenuC;
import com.mao.cn.learnDevelopProject.designPattern.Pattern11_composePattern.composemode.WaitressC;

/**
 * @author zhangkun
 * @time 2020-02-23 10:58
 */
public class MainTest {

    public static void main(String[] args) {
        WaitressC waitressC  = new WaitressC();

        CakeHouseMenuC cakeHouseMenuC = new CakeHouseMenuC();
        DinnerMenuC dinnerMenuC = new DinnerMenuC();
        waitressC.addIterator(cakeHouseMenuC);
        waitressC.addIterator(dinnerMenuC);
        waitressC.printMenu();
        System.out.println("****Vegetable****");
        waitressC.printVegetableMenu();

    }
}
