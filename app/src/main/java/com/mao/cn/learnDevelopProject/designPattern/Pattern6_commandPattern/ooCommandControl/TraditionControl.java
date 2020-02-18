package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.ooCommandControl;

import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.Control;
import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Light;
import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Stereo;

/**
 * @author zhangkun
 * @time 2020-02-18 10:22
 */
public class TraditionControl implements Control {

    private Light light;
    private Stereo stereo;

    public TraditionControl(Light light, Stereo stereo) {
        this.light = light;
        this.stereo = stereo;
    }

    // 遥控器 第一个按钮是灯 第二个按钮是音响 第三个按钮是音响的声音增加和减少

    @Override
    public void onButton(int slot) {
        switch (slot){
            case 0:
                light.on();
                break;
            case 1:
                stereo.on();
                break;
            case 2:
                int vol =stereo.getVol();
                if (vol < 11) {
                    stereo.setVol(++vol);
                }
                break;
        }

    }

    @Override
    public void offButton(int slot) {
        switch (slot){
            case 0:
                light.off();
                break;
            case 1:
                stereo.off();
                break;
            case 2:
                int vol =stereo.getVol();
                if (vol > 0) {
                    stereo.setVol(--vol);
                }
                break;
        }
    }

    @Override
    public void undoButton() {

    }
}
