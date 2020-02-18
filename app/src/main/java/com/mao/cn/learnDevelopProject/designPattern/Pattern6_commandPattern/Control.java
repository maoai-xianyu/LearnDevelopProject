package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern;

/**
 * @author zhangkun
 * @time 2020-02-18 10:13
 */
public interface Control {

    public void onButton(int slot);

    public void offButton(int slot);

    // 扩展方法
    public void undoButton();
}
