package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.arch.lifecycle.ViewModel;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.Random;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 */
public class JetPackActivityViewModel extends ViewModel {

    private String TAG = this.getClass().getSimpleName();

    private String myRandomNumber;

    public String getNumber() {
        LogU.i(TAG + " Get number");
        if (myRandomNumber == null) {
            createNumber();
        }
        return myRandomNumber;
    }

    private void createNumber() {
        LogU.i(TAG + " create number");
        Random random = new Random();
        myRandomNumber = "Number:" + (random.nextInt(10 - 1) + 1);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogU.i(TAG+" ViewModel destory");
    }
}
