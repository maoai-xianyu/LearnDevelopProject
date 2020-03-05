package com.mao.cn.learnDevelopProject.designPattern.Pattern20_mediatorPattern;

import java.util.HashMap;

/**
 * @author zhangkun
 * @time 2020-03-05 17:56
 */
public class ConcreteMediator implements Mediator {

    private HashMap<String, Colleague> mColleagueHashMap;
    private HashMap<String, String> interMap;

    public ConcreteMediator() {
        mColleagueHashMap = new HashMap<>();
        interMap = new HashMap<>();
    }

    @Override
    public void Register(String colleagueName, Colleague colleague) {
        mColleagueHashMap.put(colleagueName, colleague);
        if (colleague instanceof Alarm) {
            interMap.put("Alarm", colleagueName);
        } else if (colleague instanceof CoffeeMachine) {
            interMap.put("CoffeeMachine", colleagueName);
        } else if (colleague instanceof TV) {
            interMap.put("TV", colleagueName);
        } else if (colleague instanceof Curtains) {
            interMap.put("Curtains", colleagueName);
        }

    }

    @Override
    public void GetMessage(int stateChange, String colleagueName) {
        if (mColleagueHashMap.get(colleagueName) instanceof Alarm) {
            if (stateChange == 0) {
                ((CoffeeMachine) mColleagueHashMap.get(interMap.get("CoffeeMachine"))).startCoffee();
                ((TV) mColleagueHashMap.get(interMap.get("TV"))).StartTV();
            } else if (stateChange == 1) {
                ((TV) mColleagueHashMap.get(interMap.get("TV"))).StopTV();
            }
        } else if (mColleagueHashMap.get(colleagueName) instanceof CoffeeMachine) {
            ((Curtains) (mColleagueHashMap.get(interMap.get("Curtains")))).upCurtains();
        } else if (mColleagueHashMap.get(colleagueName) instanceof TV) {

        } else if (mColleagueHashMap.get(colleagueName) instanceof Curtains) {

        }

    }

    @Override
    public void SendMessage() {

    }
}
