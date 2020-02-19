package com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.classAdapter;

import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.duck.DuckAdapter;
import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.duck.GreenHeadDuckAdapter;
import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.turkey.WildTurkey;

/**
 * @author zhangkun
 * @time 2020-02-19 10:24
 */
public class ClassAdapterTest {

    public static void main(String[] args) {
        GreenHeadDuckAdapter greenHeadDuckAdapter = new GreenHeadDuckAdapter();
        WildTurkey wildTurkey = new WildTurkey();

        DuckAdapter duckTurkey = new ClassTurkeyAdapter();
        System.out.println("***火鸡***");
        wildTurkey.gobble();
        wildTurkey.fly();
        System.out.println("***鸭子***");
        greenHeadDuckAdapter.quack();
        greenHeadDuckAdapter.fly();
        System.out.println("***火鸡类适配鸭子***");
        duckTurkey.quack();
        duckTurkey.fly();
    }
}
