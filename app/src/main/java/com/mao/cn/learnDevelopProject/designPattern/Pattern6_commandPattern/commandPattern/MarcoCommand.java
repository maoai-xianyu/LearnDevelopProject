package com.mao.cn.learnDevelopProject.designPattern.Pattern6_commandPattern.commandPattern;

/**
 * @author zhangkun
 * @time 2020-02-18 12:00
 */
public class MarcoCommand implements CommandP {

    // 宏命令
    private CommandP[] commands;

    public MarcoCommand(CommandP[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }

    }

    @Override
    public void undo() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].undo();
        }
    }
}
