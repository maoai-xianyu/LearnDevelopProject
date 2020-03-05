package com.mao.cn.learnDevelopProject.designPattern.Pattern20_mediatorPattern;

/**
 * @author zhangkun
 * @time 2020-03-05 17:38
 */
public interface Mediator {

    public void Register(String colleagueName, Colleague colleague);

    public void GetMessage(int stateChange, String colleagueName);

    public void SendMessage();
}
