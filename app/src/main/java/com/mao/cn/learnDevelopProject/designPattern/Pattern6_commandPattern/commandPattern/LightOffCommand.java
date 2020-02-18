package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.commandPattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Light;

/**
 * @author zhangkun
 * @time 2020-02-18 11:03
 */
public class LightOffCommand implements CommandP {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
