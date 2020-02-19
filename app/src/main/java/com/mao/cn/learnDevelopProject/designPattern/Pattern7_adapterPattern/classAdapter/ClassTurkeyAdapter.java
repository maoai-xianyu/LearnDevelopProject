package com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.classAdapter;

import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.duck.DuckAdapter;
import com.mao.cn.learnDevelopProject.designPattern.Pattern7_adapterPattern.turkey.WildTurkey;

/**
 * @author zhangkun
 * @time 2020-02-19 11:33
 */
public class ClassTurkeyAdapter extends WildTurkey implements DuckAdapter {

    @Override
    public void quack() {
        super.gobble();
    }

    @Override
    public void fly(){
        super.fly();
        super.fly();
        super.fly();

    }

}
