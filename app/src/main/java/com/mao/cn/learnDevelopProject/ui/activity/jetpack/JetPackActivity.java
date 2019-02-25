package com.mao.cn.learnDevelopProject.ui.activity.jetpack;

/**
 * zkangkun
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;


public class JetPackActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_jet_pack);

        LogU.i(TAG + "Owner onCreate");
        getLifecycle().addObserver(new JetPackActivityObserver());
    }


    @Override
    protected void onStart() {
        super.onStart();
        LogU.i(TAG + "Owner onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogU.i(TAG + "Owner onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.i(TAG + "Owner onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogU.i(TAG + "Owner onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.i(TAG + "Owner onDestroy");
    }
}
