package com.mao.cn.learnDevelopProject.ui.activity.memory;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.ui.fragment.materialDesign.LifeMeFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author zhangkun
 * @time 2020-03-06 14:42
 */
public class MemoryActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        setContentView(R.layout.aty_memory);

        LogU.e("MemoryActivity onCreate");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.rlContent, LifeMeFragment.newInstance())
                .commitAllowingStateLoss();
    }


    @Override
    protected void onStart() {
        super.onStart();
        LogU.e("MemoryActivity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogU.e("MemoryActivity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogU.e("MemoryActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogU.e("MemoryActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogU.e("MemoryActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.e("MemoryActivity onDestroy");
    }
}
