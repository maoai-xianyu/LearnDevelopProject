package com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.objectAdapter;

import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.duck.DuckAdapter;
import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.turkey.Turkey;

/**
 * @author zhangkun
 * @time 2020-02-19 10:31
 */
public class TurkeyAdapter implements DuckAdapter {

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void fly() {
        for (int i = 0; i < 6; i++) {
            turkey.fly();
        }
    }

    @Override
    public void quack() {
        turkey.gobble();
    }
}
