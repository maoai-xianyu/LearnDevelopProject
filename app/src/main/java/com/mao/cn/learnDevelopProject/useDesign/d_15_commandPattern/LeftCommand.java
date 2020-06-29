package com.mao.cn.learnDevelopProject.useDesign.d_15_commandPattern;

/**
 * @author zhangkun
 * @time 2020-06-03 15:40
 * @Description
 */
public class LeftCommand implements Command{

    private TetrisMachine mTetrisMachine;

    public LeftCommand(TetrisMachine tetrisMachine) {
        mTetrisMachine = tetrisMachine;
    }

    @Override
    public void execute() {
        mTetrisMachine.toLeft();
    }
}
