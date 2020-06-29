package com.mao.cn.learnDevelopProject.useDesign.d_15_commandPattern;

/**
 * @author zhangkun
 * @time 2020-06-03 15:44
 * @Description
 */
public class Buttons {

    private LeftCommand mLeftCommand;
    private RightCommand mRightCommand;


    public void setLeftCommand(LeftCommand leftCommand) {
        mLeftCommand = leftCommand;
    }

    public void setRightCommand(RightCommand rightCommand) {
        mRightCommand = rightCommand;
    }


    public void toLeft() {
        mLeftCommand.execute();
    }

    public void toRight() {
        mRightCommand.execute();
    }
}
