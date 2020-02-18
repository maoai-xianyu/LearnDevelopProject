package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.commandPattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.Control;

import java.util.Stack;

/**
 * @author zhangkun
 * @time 2020-02-18 11:10
 */
public class CommandModeControl implements Control {
    // 遥控器的设置
    private CommandP[] onCommands;
    private CommandP[] offCommands;
    // 扩展回退功能更
    private Stack<CommandP> commandPS = new Stack<>();

    public CommandModeControl() {

        onCommands = new CommandP[5];
        offCommands = new CommandP[5];

        NoCommandP noCommandP = new NoCommandP();

        for (int i = 0; i < onCommands.length; i++) {
            onCommands[i] = noCommandP;
            offCommands[i] = noCommandP;
        }
    }

    public void setCommamd(int slot, CommandP onCommandP, CommandP offCommandP) {
        onCommands[slot] = onCommandP;
        offCommands[slot] = offCommandP;
    }

    @Override
    public void onButton(int slot) {
        onCommands[slot].execute();
        commandPS.push(onCommands[slot]);
    }

    @Override
    public void offButton(int slot) {
        offCommands[slot].execute();
        commandPS.push(offCommands[slot]);
    }

    @Override
    public void undoButton() {
        commandPS.pop().undo();
    }
}
