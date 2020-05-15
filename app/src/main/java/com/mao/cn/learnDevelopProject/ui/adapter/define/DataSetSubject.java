package com.mao.cn.learnDevelopProject.ui.adapter.define;

/**
 * @author zhangkun
 * @time 2020-03-27 22:45
 */
public interface DataSetSubject {

    public void registerObserver(DataSetObserver o);

    public void removeObserver(DataSetObserver o);

    public void notifyObservers();
}
