package com.mao.cn.learnDevelopProject.designPattern.Pattern20_mediatorPattern;

/**
 * @author zhangkun
 * @time 2020-03-05 18:00
 */
public class TV extends Colleague {

    public TV(Mediator mediator, String name) {
        super(mediator, name);
        mediator.Register(name, this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().GetMessage(stateChange,name);
    }

    public void StartTV() {
        System.out.println("It't time to StartTV!");

    }

    public void StopTV() {
        System.out.println("Stop TV");
    }
}
