package com.mao.cn.learnDevelopProject.utils.x5;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.tencent.smtt.sdk.QbSdk;

/**
 * 作者：syd_123 on 2018/9/12 15:21
 * <p>
 * csdn https://blog.csdn.net/dong19900415
 */
public class PreLoadX5Service extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initX5();
        preinitX5WebCore();
    }

    private void initX5() {
        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true);
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
        LogU.e("预加载中...");
    }

    //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
            // TODO Auto-generated method stub
            LogU.e("onViewInitFinished is " + arg0);
        }

        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
            LogU.e("预加载中...onCoreInitFinished");

        }
    };

    private void preinitX5WebCore() {

        if (!QbSdk.isTbsCoreInited()) {

            // preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
            LogU.e("预加载中...preinitX5WebCore");
            QbSdk.preInit(getApplicationContext(), null);// 设置X5初始化完成的回调接口

        }
    }

}