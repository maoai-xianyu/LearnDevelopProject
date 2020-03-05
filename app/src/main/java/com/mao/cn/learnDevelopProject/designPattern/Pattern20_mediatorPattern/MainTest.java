package com.mao.cn.learnDevelopProject.designPattern.Pattern20_mediatorPattern;

/**
 * @author zhangkun
 * @time 2020-03-05 22:31
 */
public class MainTest {

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        Alarm alarm = new Alarm(mediator, "Alarm");
        CoffeeMachine coffeeMachine = new CoffeeMachine(mediator, "CoffeeMachine");
        Curtains curtains = new Curtains(mediator, "Curtains");
        TV tv = new TV(mediator, "TV");
        alarm.sendAlarm(0);
        coffeeMachine.finishCoffee();
        alarm.sendAlarm(1);

    }
}
