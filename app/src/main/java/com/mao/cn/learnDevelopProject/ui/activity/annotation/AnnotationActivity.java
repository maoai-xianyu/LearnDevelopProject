package com.mao.cn.learnDevelopProject.ui.activity.annotation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;

public class AnnotationActivity extends AppCompatActivity {


    @ViewById(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_annotation);

        ViewUtils.inject(this);

        tv.setText("这是自定义的反射注入 findViewById");
    }
}
