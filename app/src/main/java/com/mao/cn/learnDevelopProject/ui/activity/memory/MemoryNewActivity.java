package com.mao.cn.learnDevelopProject.ui.activity.memory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.utils.CommUtils;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryNewActivity extends AppCompatActivity {

    private static int a = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_memory_new);

        CommUtils commUtils = CommUtils.getInstance(this);


        /*mHandler.sendEmptyMessage(0);

        String test = "test";

        Map<String,String> map= new WeakHashMap<>();
        map.put(test,"test-1");
        test = null;*/


    }

    public static void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int b = a;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                int b = a;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 20000);

    }


    static class RunThread extends Thread {

        @Override
        public void run() {

            int b = a;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // mHandler 是匿名内部类的实例，会应用外部对象 MemoryNewActivity.this,如果Handler在Activity退出的时候，
    // 它可能还活着，这时候就会内存泄漏
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    // 加载数据
                    break;
            }
        }
    };


    private int c = 10;

    static class MyHandler extends Handler {

        // 直接持有一个外部类的强引用，会内存泄漏
        //private MemoryNewActivity mMemoryNewActivity;

        // 设置软引用保存，当内存一发生GC的时候就会回收
        private WeakReference<MemoryNewActivity> mMemoryNewActivity;

        public MyHandler(MemoryNewActivity memoryNewActivity) {
            mMemoryNewActivity = new WeakReference<>(memoryNewActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MemoryNewActivity memoryNewActivity = mMemoryNewActivity.get();
            if (memoryNewActivity == null || memoryNewActivity.isFinishing()) return;
            switch (msg.what) {
                case 0:
                    // 加载数据
                    int b = memoryNewActivity.c;
                    break;
            }
        }
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
        LogU.e("MemoryActivity onPause");
    }
}
