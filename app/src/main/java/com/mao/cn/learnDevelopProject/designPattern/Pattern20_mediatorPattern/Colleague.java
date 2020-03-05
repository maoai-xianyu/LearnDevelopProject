package com.mao.cn.learnDevelopProject.designPattern.Pattern20_mediatorPattern;

/**
 * @author zhangkun
 * @time 2020-03-05 17:53
 */
public abstract class Colleague {
    private Mediator mMediator;
    public String name;

    public Colleague(Mediator mediator, String name) {
        mMediator = mediator;
        this.name = name;
    }

    public Mediator getMediator() {
        return mMediator;
    }

    public abstract void sendMessage(int stateChange);
}
