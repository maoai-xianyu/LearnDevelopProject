package com.mao.cn.learnDevelopProject.designPattern.Pattern9_templatePattern.templateAround;

import java.util.Arrays;

/**
 * @author zhangkun
 * @time 2020-02-20 16:52
 */
public class MainTest {
    public static void main(String[] args) {
        ArrayDuck[] arrayDucks = {
                new ArrayDuck("Duck1", (int) (Math.random() * 100)),
                new ArrayDuck("Duck2", (int) (Math.random() * 100)),
                new ArrayDuck("Duck3", (int) (Math.random() * 100)),
                new ArrayDuck("Duck4", (int) (Math.random() * 100)),
                new ArrayDuck("Duck5", (int) (Math.random() * 100)),
                new ArrayDuck("Duck6", (int) (Math.random() * 100)),
                new ArrayDuck("Duck7", (int) (Math.random() * 100)),
                new ArrayDuck("Duck8", (int) (Math.random() * 100)),
                new ArrayDuck("Duck9", (int) (Math.random() * 100)),
                new ArrayDuck("Duck10", (int) (Math.random() * 100))};
        System.out.println("before sort:");
        display(arrayDucks);
        Arrays.sort(arrayDucks);
        System.out.println("after sort:");
        display(arrayDucks);
    }

    private static void display(ArrayDuck[] arrayDucks) {

        for (int i = 0; i < arrayDucks.length; i++) {
            System.out.println(arrayDucks[i].toString());
        }
    }


}
