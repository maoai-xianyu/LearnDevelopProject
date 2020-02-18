package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.commandPattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Light;
import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.command.Stereo;

/**
 * @author zhangkun
 * @time 2020-02-18 11:21
 */
public class ControlPTest {
    public static void main(String[] args) {
        CommandModeControl commandModeControl = new CommandModeControl();

        Light lightBed = new Light("BedRoom");
        Light lightKit = new Light("KitRoom");

        LightOnCommand lightOnCommand = new LightOnCommand(lightBed);
        LightOffCommand lightOffCommand = new LightOffCommand(lightBed);
        LightOnCommand lightOnCommandKit = new LightOnCommand(lightKit);
        LightOffCommand lightOffCommandKit = new LightOffCommand(lightKit);

        Stereo stereo = new Stereo();

        StereoOnCommand stereoOnCommand = new StereoOnCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);
        StereoVolAddCommand stereoVolAddCommand = new StereoVolAddCommand(stereo);
        StereoVolSubCommand stereoVolSubCommand = new StereoVolSubCommand(stereo);

        commandModeControl.setCommamd(0, lightOnCommand, lightOffCommand);
        commandModeControl.setCommamd(1, lightOnCommandKit, lightOffCommandKit);

        commandModeControl.setCommamd(2, stereoOnCommand, stereoOffCommand);
        commandModeControl.setCommamd(3, stereoVolAddCommand, stereoVolSubCommand);


        commandModeControl.onButton(0);
        //commandModeControl.offButton(0);
        commandModeControl.undoButton();

        commandModeControl.onButton(1);
        commandModeControl.undoButton();

        commandModeControl.onButton(2);
        commandModeControl.onButton(3);

        //commandModeControl.offButton(3);
        commandModeControl.undoButton();
        commandModeControl.offButton(2);

        CommandP[] onCommands = {lightOnCommand, lightOnCommandKit};
        MarcoCommand onMarcoCommand = new MarcoCommand(onCommands);
        CommandP[] offCommands = {lightOffCommand, lightOffCommandKit};
        MarcoCommand offMarcoCommand = new MarcoCommand(offCommands);
        commandModeControl.setCommamd(4,onMarcoCommand,offMarcoCommand);

        commandModeControl.onButton(4);
        commandModeControl.offButton(4);
    }
}
