package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.ooCommandControl;

import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.Control;
import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Light;
import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Stereo;

/**
 * @author zhangkun
 * @time 2020-02-18 10:27
 */
public class ControlTest {

    public static void main(String[] args) {

        Light light = new Light("Bedroom");
        Stereo stereo = new Stereo();

        Control control = new TraditionControl(light, stereo);

        control.onButton(0);
        control.offButton(0);
        control.onButton(1);
        control.onButton(2);
        control.offButton(2);
        control.offButton(1);
    }
}
