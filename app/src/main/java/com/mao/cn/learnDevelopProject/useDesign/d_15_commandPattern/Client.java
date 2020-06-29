package com.mao.cn.learnDevelopProject.useDesign.d_15_commandPattern;

/**
 * @author zhangkun
 * @time 2020-06-03 15:41
 * @Description 命令模式
 */
public class Client {
    public static void main(String[] args) {

        TetrisMachine tetrisMachine = new TetrisMachine();

        LeftCommand leftCommand  = new LeftCommand(tetrisMachine);
        RightCommand rightCommand  = new RightCommand(tetrisMachine);

        Buttons buttons = new Buttons();

        buttons.setLeftCommand(leftCommand);
        buttons.setRightCommand(rightCommand);


        buttons.toLeft();
        buttons.toRight();

    }
}
