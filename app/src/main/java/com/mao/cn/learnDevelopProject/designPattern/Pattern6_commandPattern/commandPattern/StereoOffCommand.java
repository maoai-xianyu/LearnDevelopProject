package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.commandPattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Stereo;

/**
 * @author zhangkun
 * @time 2020-02-18 11:03
 */
public class StereoOffCommand implements CommandP {

    private Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.on();
        stereo.setCd();
    }
}
