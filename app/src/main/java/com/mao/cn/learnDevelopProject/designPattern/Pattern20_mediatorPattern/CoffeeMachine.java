package com.mao.cn.learnDevelopProject.designPattern.Pattern20_mediatorPattern;

/**
 * @author zhangkun
 * @time 2020-03-05 17:59
 */
public class CoffeeMachine extends Colleague {

    public CoffeeMachine(Mediator mediator, String name) {
        super(mediator, name);
        mediator.Register(name, this);
    }


    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().GetMessage(stateChange, name);
    }

    public void startCoffee() {
        System.out.println("It's time to startCoffee!");

    }

    public void finishCoffee() {
        System.out.println("after 5 minutes finishCoffee!");
        sendMessage(0);

    }
}
