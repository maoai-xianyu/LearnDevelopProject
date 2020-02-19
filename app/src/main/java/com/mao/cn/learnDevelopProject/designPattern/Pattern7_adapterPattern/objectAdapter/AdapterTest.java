package com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.objectAdapter;

import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.duck.DuckAdapter;
import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.duck.GreenHeadDuckAdapter;
import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.turkey.WildTurkey;

/**
 * @author zhangkun
 * @time 2020-02-19 10:24
 */
public class AdapterTest {

    public static void main(String[] args) {
        GreenHeadDuckAdapter greenHeadDuckAdapter = new GreenHeadDuckAdapter();
        WildTurkey wildTurkey = new WildTurkey();

        DuckAdapter duckTurkey = new TurkeyAdapter(wildTurkey);
        System.out.println("***火鸡***");
        wildTurkey.gobble();
        wildTurkey.fly();
        System.out.println("***鸭子***");
        greenHeadDuckAdapter.quack();
        greenHeadDuckAdapter.fly();
        System.out.println("***火鸡适配鸭子***");
        duckTurkey.quack();
        duckTurkey.fly();
    }
}
