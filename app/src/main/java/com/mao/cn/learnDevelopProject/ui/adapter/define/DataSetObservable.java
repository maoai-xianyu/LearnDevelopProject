package com.mao.cn.learnDevelopProject.ui.adapter.define;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-03-27 22:28
 */
public class DataSetObservable implements DataSetSubject {


    public List<DataSetObserver> mDataSetObservers;

    public DataSetObservable() {
        mDataSetObservers = new ArrayList<>();
    }


    @Override
    public void registerObserver(DataSetObserver o) {
        mDataSetObservers.add(o);

    }

    @Override
    public void removeObserver(DataSetObserver o) {
        if (mDataSetObservers.contains(o)) {
            mDataSetObservers.remove(o);
        }
    }


    @Override
    public void notifyObservers() {
        for (DataSetObserver observer : mDataSetObservers) {
            observer.onChange();
        }
    }
}
