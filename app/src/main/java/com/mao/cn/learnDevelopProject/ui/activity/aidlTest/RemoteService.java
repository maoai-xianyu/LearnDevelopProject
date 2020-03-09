package com.mao.cn.learnDevelopProject.ui.activity.aidlTest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;

import com.mao.cn.learnDevelopProject.IConnectionService;
import com.mao.cn.learnDevelopProject.IMessageService;
import com.mao.cn.learnDevelopProject.IServiceManager;
import com.mao.cn.learnDevelopProject.MessageReceiveListener;
import com.mao.cn.learnDevelopProject.model.Message;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;

import java.util.ArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 管理和提供子进程的连接和消息服务
 */
public class RemoteService extends Service {

    private boolean isConnect = false;

    // 定义主线程的handler
    private Handler mHandler = new Handler(Looper.getMainLooper());

    // 存储消息的监听
    private ArrayList<MessageReceiveListener> mMessageReceiveListeners = new ArrayList<>();

    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;


    private ScheduledFuture mScheduledFuture;
    // 子进程
    private IConnectionService mIConnectionService = new IConnectionService.Stub() {
        @Override
        public void connect() throws RemoteException {
            try {

                // 不用 oneway 主进程调用 connect 会被子进程阻塞。
                Thread.sleep(5000);
                LogU.d("mIConnectionService connect");
                isConnect = true;

                //在主线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show("connect");
                    }
                });

                // 切换到主线程中
                /*
                Observable.timer(2000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long s) {
                                ToastUtils.show("rx connect");
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                LogU.e(throwable.getMessage());
                            }
                        });*/


                mScheduledFuture = mScheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {

                        if (mMessageReceiveListeners != null && mMessageReceiveListeners.size() > 0) {
                            for (MessageReceiveListener messageReceiveListener : mMessageReceiveListeners) {

                                Message message = new Message();
                                message.setContent("this message for remote");
                                try {
                                    messageReceiveListener.onReceiveMessage(message);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                }, 5000, 5000, TimeUnit.MILLISECONDS);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void disconnect() throws RemoteException {
            isConnect = false;
            LogU.d("mIConnectionService disconnect");
            mScheduledFuture.cancel(true);
            //在主线程执行
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.show("disconnect");
                }
            });


        }

        @Override
        public boolean isConnected() throws RemoteException {
            LogU.d("mIConnectionService isConnected  " + isConnect);
            //在主线程执行
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.show("isConnected " + isConnect);
                }
            });
            return isConnect;
        }
    };

    private IMessageService mIMessageService = new IMessageService.Stub() {
        @Override
        public void sendMessage(Message message) throws RemoteException {

            LogU.d("mIMessageService sendMessage  " + message);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.show("mIMessageService " + message.getContent());
                }
            });
            if (isConnect) {
                message.setSendSuccess(true);
            } else {
                message.setSendSuccess(false);
            }
        }

        @Override
        public void registerMessageReceiveListener(MessageReceiveListener messageReceiveListener) throws RemoteException {
            if (messageReceiveListener != null) {
                mMessageReceiveListeners.add(messageReceiveListener);
            }
        }

        @Override
        public void unRegisterMessageReceiveListener(MessageReceiveListener messageReceiveListener) throws RemoteException {
            if (messageReceiveListener != null) {
                mMessageReceiveListeners.remove(messageReceiveListener);
            }
        }
    };

    private IServiceManager mIServiceManager = new IServiceManager.Stub() {
        @Override
        public IBinder getService(String serviceName) throws RemoteException {

            if (IConnectionService.class.getSimpleName().equals(serviceName)) {
                return mIConnectionService.asBinder();
            } else if (IMessageService.class.getSimpleName().equals(serviceName)) {
                return mIMessageService.asBinder();
            }
            return null;
        }
    };

    // 主进程
    @Override
    public IBinder onBind(Intent intent) {

        return mIServiceManager.asBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
    }
}
