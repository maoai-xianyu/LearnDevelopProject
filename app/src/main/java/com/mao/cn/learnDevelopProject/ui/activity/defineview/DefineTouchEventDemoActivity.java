package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.TouchView;
import com.mao.cn.learnDevelopProject.widgets.TouchViewGroup;
import com.mao.cn.learnDevelopProject.widgets.TouchViewInner;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class DefineTouchEventDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tv_header_title)
    TextView mDTextView;

    @BindView(R.id.touchView)
    TouchView mTouchView;
    @BindView(R.id.touchViewGroup)
    TouchViewGroup touchViewGroup;
    @BindView(R.id.touchViewInner)
    TouchViewInner touchViewInner;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_view_touch_event;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);
        mDTextView.setText("触摸事件");

        mTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.d("------> TouchView setOnClickListener onClick ");
            }
        });

        mTouchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        LogU.d("------> TouchView setOnTouchListener onTouch   ACTION_DOWN");
                        break;

                    case MotionEvent.ACTION_MOVE:
                        LogU.d("------> TouchView setOnTouchListener onTouch   ACTION_MOVE");
                        break;

                    case MotionEvent.ACTION_UP:
                        LogU.d("------> TouchView setOnTouchListener onTouch   ACTION_UP");
                        break;

                }
                return false;
            }
        });


         /*touchViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.d("------> touchViewGroup setOnClickListener onClick ");
            }
        });
        touchViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogU.d("------> touchViewGroup setOnTouchListener onTouch "+event.getAction());
                return false;
            }
        });*/

        /*touchViewInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogU.d("------> touchViewInner setOnClickListener onClick ");
            }
        });*/

        touchViewInner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogU.d("------> touchViewInner setOnTouchListener onTouch "+event.getAction());
                return false;
            }
        });



    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogU.d("activity  ACTION_DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                LogU.d("activity  ACTION_MOVE");
                break;

            case MotionEvent.ACTION_UP:
                LogU.d("activity  ACTION_UP");
                break;

        }
        return super.onTouchEvent(event);
    }
}
