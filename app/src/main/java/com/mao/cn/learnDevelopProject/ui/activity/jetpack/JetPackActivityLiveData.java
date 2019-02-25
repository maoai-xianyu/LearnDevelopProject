package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.Random;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 */
public class JetPackActivityLiveData extends ViewModel {

    private String TAG = this.getClass().getSimpleName();

    private MutableLiveData<String> myRandomNumber;

    public MutableLiveData<String> getNumber() {
        LogU.i(TAG + " Get number");
        if (myRandomNumber == null) {
            myRandomNumber = new MutableLiveData<>();
            createNumber();
        }
        return myRandomNumber;
    }

    public void createNumber() {
        LogU.i(TAG + " create number");
        Random random = new Random();
        myRandomNumber.setValue("Number:" + (random.nextInt(10 - 1) + 1));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogU.i(TAG + " ViewModel destory");
    }
}
