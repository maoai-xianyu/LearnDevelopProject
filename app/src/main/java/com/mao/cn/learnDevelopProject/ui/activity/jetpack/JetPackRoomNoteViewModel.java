package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;

/**
 * author:  zhangkun .
 * date:    on 2019/2/25.
 */
public class JetPackRoomNoteViewModel extends AndroidViewModel {


    private String TAG = this.getClass().getSimpleName();

    public JetPackRoomNoteViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogU.i(TAG + " viewmodel destroyed");
    }
}
