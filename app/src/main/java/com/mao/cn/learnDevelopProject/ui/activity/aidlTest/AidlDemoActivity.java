package com.mao.cn.learnDevelopProject.ui.activity.aidlTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.IConnectionService;
import com.mao.cn.learnDevelopProject.IMessageService;
import com.mao.cn.learnDevelopProject.IServiceManager;
import com.mao.cn.learnDevelopProject.MessageReceiveListener;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.model.Message;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class AidlDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton idBack;
    @BindView(R.id.tvConnect)
    TextView tvConnect;
    @BindView(R.id.tvDisconnect)
    TextView tvDisconnect;
    @BindView(R.id.tvisConnect)
    TextView tvisConnect;
    @BindView(R.id.tvMsgS)
    TextView tvMsgS;
    @BindView(R.id.tvMsgR)
    TextView tvMsgR;
    @BindView(R.id.tvMsgUR)
    TextView tvMsgUR;
    @BindView(R.id.tvMsgMessenger)
    TextView tvMsgMessenger;


    private IConnectionService mIConnectionServiceProxy;
    private IMessageService mIMessageServiceProxy;
    private IServiceManager mIServiceManager;
    private Messenger mMessengerProxy;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            // 接收服务端发来的消息
            Bundle data = msg.getData();
            data.setClassLoader(Message.class.getClassLoader());
            Message message = data.getParcelable("message");
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.show("客户端收到服务器的消息" + message.getContent());
                }
            }, 3000);
        }
    };

    private Messenger clientMessenger = new Messenger(mHandler);

    private MessageReceiveListener mMessageReceiveListener = new MessageReceiveListener.Stub() {
        @Override
        public void onReceiveMessage(Message message) throws RemoteException {

            new Handler(Looper.getMainLooper()).post(
                    new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.show("message " + message.getContent());
                        }
                    }
            );
        }
    };

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_aidl_demo;
    }

    @Override
    public void initView() {
        idBack.setVisibility(View.VISIBLE);

        Intent mIntent = new Intent(this, RemoteService.class);

        bindService(mIntent, conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            try {
                mIServiceManager = IServiceManager.Stub.asInterface(service);
                mIConnectionServiceProxy = IConnectionService.Stub.asInterface(mIServiceManager.getService(IConnectionService.class.getSimpleName()));
                mIMessageServiceProxy = IMessageService.Stub.asInterface(mIServiceManager.getService(IMessageService.class.getSimpleName()));
                mMessengerProxy = new Messenger(mIServiceManager.getService(Messenger.class.getSimpleName()));
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    };


    @Override
    public void setListener() {

        RxView.clicks(idBack).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.toString()));

        RxView.clicks(tvConnect).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {

                    try {
                        LogU.d("主点击 connect" + System.currentTimeMillis());
                        mIConnectionServiceProxy.connect();
                        LogU.d("主响应 connect" + System.currentTimeMillis());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }, throwable -> LogU.e(throwable.toString()));

        RxView.clicks(tvDisconnect).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    try {
                        mIConnectionServiceProxy.disconnect();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }, throwable -> LogU.e(throwable.toString()));

        RxView.clicks(tvisConnect).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    boolean connected = false;
                    try {
                        connected = mIConnectionServiceProxy.isConnected();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    LogU.d(" connected " + connected);

                    ToastUtils.show(" connected " + String.valueOf(connected));
                }, throwable -> LogU.e(throwable.toString()));


        RxView.clicks(tvMsgS).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Message message = new Message();
                    message.setContent("this is a message");
                    try {
                        mIMessageServiceProxy.sendMessage(message);
                        LogU.d(" message send " + message);
                        LogU.d(" message send success " + message.isSendSuccess());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }, throwable -> LogU.e(throwable.toString()));


        RxView.clicks(tvMsgR).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    try {
                        mIMessageServiceProxy.registerMessageReceiveListener(mMessageReceiveListener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    LogU.d(" message r listener ");

                }, throwable -> LogU.e(throwable.toString()));


        RxView.clicks(tvMsgUR).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {

                    try {
                        mIMessageServiceProxy.unRegisterMessageReceiveListener(mMessageReceiveListener);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    LogU.d(" message ur listener ");

                }, throwable -> LogU.e(throwable.toString()));

        RxView.clicks(tvMsgMessenger).throttleFirst(ValueMaps.Time.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    try {
                        Message message = new Message();
                        message.setContent("this msg is from messenger client");
                        android.os.Message msg = new android.os.Message();
                        msg.replyTo = clientMessenger;
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("message", message);
                        msg.setData(bundle);
                        mMessengerProxy.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }, throwable -> LogU.e(throwable.toString()));
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
