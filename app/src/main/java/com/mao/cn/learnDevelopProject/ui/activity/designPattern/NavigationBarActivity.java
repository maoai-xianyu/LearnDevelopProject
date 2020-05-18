package com.mao.cn.learnDevelopProject.ui.activity.designPattern;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.useDesign.d_02_navigationbar.navigationBar.NavigationBar;

public class NavigationBarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_naviagtion_bar);

        ConstraintLayout parent = findViewById(R.id.rootView);
        NavigationBar navigationBar = new NavigationBar.Builder(this, R.layout.ui_navigation_toolbar, parent)
                .setText(R.id.tvHeaderTitle, "返回")
                .setOnClickListener(R.id.tvHeaderTitle, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .create();



        // 1. 如果想设置其他属性？比如文字的大小，比如颜色，设置图片?等等
        TextView textView = navigationBar.findViewById(R.id.tvHeaderTitle);
        textView.setVisibility(View.GONE);
        // 2. 有时候并不关注 id 以及 我们的头部的样式，大部分情况是类似，必须还要提供一个默认的

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
