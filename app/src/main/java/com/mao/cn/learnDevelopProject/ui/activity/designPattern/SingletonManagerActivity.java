package com.mao.cn.learnDevelopProject.ui.activity.designPattern;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern.manager.ActivityManager;
import com.mao.cn.learnDevelopProject.useDesign.d_02_navigationbar.navigationBar.DefaultNavigationBar;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class SingletonManagerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_singleton_design);
        LogU.e("MemoryActivity onCreate");
        ActivityManager.getInstance().attach(this);

        ConstraintLayout root = findViewById(R.id.clRoot);

        DefaultNavigationBar defaultNavigationBar = new DefaultNavigationBar
                .Builder(this, root)
                .setText("返回")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .create();

    }

    public void exitApp(View view) {
        ActivityManager.getInstance().exitApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogU.e("MemoryActivity onDestroy");
        ActivityManager.getInstance().detach(this);
    }
}
