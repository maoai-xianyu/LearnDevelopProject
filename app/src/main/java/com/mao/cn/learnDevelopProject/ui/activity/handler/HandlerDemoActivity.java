package com.mao.cn.learnDevelopProject.ui.activity.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class HandlerDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.textView2)
    TextView textView2;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 主线程
            String obj = (String) msg.obj;
            textView2.setText(obj);
        }
    };

    private Handler mHandlerRun = new Handler();

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandlerRun.postDelayed(mRunnable,1000);
        }
    };

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_handler_demo;
    }

    @Override

    public void initView() {

        idBack.setVisibility(View.VISIBLE);

        //mHandlerRun.post(mRunnable);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 子线程
                Message message1 = Message.obtain();
                message1.obj = "Handler Test1";
                mHandler.sendMessage(message1);

                Message message2 = new Message();
                message2.obj = "Handler Test 2";
                mHandler.sendMessageDelayed(message2, 1000);

                Message message3 = new Message();
                message3.obj = "Handler Test 3";
                mHandler.sendMessageDelayed(message3, 500);

                // 在子线程中new Handler() 会报错
                // Can't create handler inside thread Thread[Thread-3,5,main] that has not called Looper.prepare()
                // Handler handler = new Handler();
                // 换另外一种
                Looper.prepare();
                Handler handler = new Handler();
                Looper.loop();

            }
        }).start();

    }

    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
