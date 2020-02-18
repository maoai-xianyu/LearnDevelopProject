package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.commandPattern;

/**
 * @author zhangkun
 * @time 2020-02-18 11:02
 */
public interface CommandP {
    public void execute();

    // 撤销 回退功能 扩展
    public void undo();
}
