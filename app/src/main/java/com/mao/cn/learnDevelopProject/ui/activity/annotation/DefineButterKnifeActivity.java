package com.mao.cn.learnDevelopProject.ui.activity.annotation;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.butterknife.DefineButterKnife;
import com.butterknife.DefineUnbinder;
import com.butterknife.annontations.DefineBindView;
import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;


public class DefineButterKnifeActivity extends AppCompatActivity {


    @DefineBindView(R.id.ib_header_back)
    ImageButton idBack;
    @DefineBindView(R.id.textView2)
    TextView tv;


    private DefineUnbinder mDefineUnbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_define_butterknife_demo);

        mDefineUnbinder = DefineButterKnife.bind(this);

        idBack.setVisibility(View.VISIBLE);
        tv.setText("这是自定义的注解 define_butter_knife");

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDefineUnbinder.unbind();

    }
}
