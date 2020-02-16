package com.mao.cn.learnDevelopProject.designPattern.Pattern2_observerPattern.observerDefineSt.inter;

/**
 * @author zhangkun
 * @time 2020-02-16 13:40
 */
public interface Subject {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers();

}
