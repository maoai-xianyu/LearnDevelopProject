package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.commandPattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Stereo;

/**
 * @author zhangkun
 * @time 2020-02-18 11:03
 */
public class StereoVolSubCommand implements CommandP {

    private Stereo stereo;

    public StereoVolSubCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        int vol = stereo.getVol();
        if (vol > 0) {
            stereo.setVol(--vol);
        }
    }

    @Override
    public void undo() {
        int vol = stereo.getVol();
        if (vol < 11) {
            stereo.setVol(++vol);
        }
    }
}
