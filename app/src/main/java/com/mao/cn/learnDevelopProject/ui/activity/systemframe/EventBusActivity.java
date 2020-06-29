package com.mao.cn.learnDevelopProject.ui.activity.systemframe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.butterknife.DefineButterKnife;
import com.butterknife.DefineUnbinder;
import com.butterknife.annontations.DefineBindView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.model.EventModel;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;



public class EventBusActivity extends AppCompatActivity {


    @DefineBindView(R.id.ib_header_back)
    ImageButton idBack;
    @DefineBindView(R.id.test)
    TextView tv;

    private DefineUnbinder mDefineUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_event_bus);
        mDefineUnbinder = DefineButterKnife.bind(this);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventModel("大南"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 订阅
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 取消
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDefineUnbinder.unbind();
    }

    /**
     * threadMode 执行的线程方式
     * priority 执行的优先级 值越大优先级越高
     * sticky 粘性事件
     * @param model
     */
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100,sticky = true)
    public void onEventModel(EventModel model) {
        LogU.d(" ... " + model.getName());
    }
}
