package com.mao.cn.learnDevelopProject.ui.activity.memory;

import android.os.Bundle;
import android.widget.TextView;

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

    private TextView mTextView;

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

        mTextView = findViewById(R.id.tv);

        LogU.d(" mTextView  = "+ mTextView.getMeasuredHeight()); // mTextView = 0

        mTextView.post(new Runnable() {
            // 保存到 HandlerActionQueue 中，什么都没有干
            // 会在 dispatchAttachedToWindow 中执行 executeActions()
            // dispatchAttachedToWindow() 会在测量完毕之后调用
            @Override
            public void run() {
                LogU.d(" mTextView post = "+ mTextView.getMeasuredHeight()); // mTextView = 110

            }
        });

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
        LogU.d(" mTextView onResume = "+ mTextView.getMeasuredHeight()); // mTextView = 0
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
