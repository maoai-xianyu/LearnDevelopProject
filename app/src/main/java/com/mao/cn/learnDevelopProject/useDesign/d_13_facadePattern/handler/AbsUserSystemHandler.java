package com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler;

/**
 * @author zhangkun
 * @time 2020-06-01 21:58
 * @Description 责任链设计模式-抽象处理者角色
 */
public abstract class AbsUserSystemHandler implements IUserSystemHandler<AbsUserSystemHandler>, IUserSystem {

    private AbsUserSystemHandler nextHandler;

    public AbsUserSystemHandler getNextHandler() {
        return nextHandler;
    }

    @Override
    public void nextHandler(AbsUserSystemHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
