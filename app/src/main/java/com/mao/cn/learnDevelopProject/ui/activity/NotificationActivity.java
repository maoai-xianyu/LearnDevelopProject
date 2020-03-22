// +----------------------------------------------------------------------
// | Project:   MvpProject
// +----------------------------------------------------------------------
// | CreateTime: 06/09/2017 11:17 上午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IMain;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.NotificationUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

import static androidx.core.app.NotificationCompat.PRIORITY_DEFAULT;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class NotificationActivity extends BaseActivity implements IMain {

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.ib_call_phone)
    ImageButton ibCallPhone;
    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.btnSendSubscribe)
    Button btnSendSubscribe;
    @BindView(R.id.btnUtil)
    Button btnUtil;

    @BindView(R.id.btnUtil1)
    Button btnUtil1;
    private NotificationUtils notificationUtils;


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_notification;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        ibCallPhone.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("通知");
        tvHeaderTitle.setVisibility(View.VISIBLE);

        //notificationUtils = new NotificationUtils(this);
        initNotification();

    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });
        RxView.clicks(btnSend).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            sendChatMsg();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnSendSubscribe).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            sendSubscribeMsg();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnUtil).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            sendCustomNotification();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        RxView.clicks(btnUtil1).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            download();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

    }

    private void download() {
        final NotificationManager mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "define_id");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("define_id", "define", NotificationManager.IMPORTANCE_HIGH);
            mBuilder.setChannelId("define_id");
            getNotificationManager().createNotificationChannel(notificationChannel);
        } else {
            mBuilder.setPriority(PRIORITY_DEFAULT);
        }

        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setOngoing(true)
                .setVibrate(new long[]{0})
                .setSound(null)
                .setTicker("notification ticker")
                .setDefaults(NotificationCompat.FLAG_LOCAL_ONLY)
                .setSmallIcon(android.R.drawable.stat_notify_chat);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        for (incr = 0; incr <= 100; incr += 5) {
                            mBuilder.setProgress(100, incr, false);
                            mBuilder.setSound(null);
                            mNotifyManager.notify(0, mBuilder.build());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        }
                        mBuilder.setContentText("Download complete").setProgress(0, 0, false);
                        mBuilder.setAutoCancel(true);
                        mBuilder.setOngoing(false);
                        mNotifyManager.notify(0, mBuilder.build());
                    }
                }
        ).start();
    }


    private void sendCustomNotification() {
        NotificationCompat.Builder builder = getNotificationBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("define_id", "define", NotificationManager.IMPORTANCE_HIGH);
            builder.setChannelId("define_id");
            getNotificationManager().createNotificationChannel(notificationChannel);
        } else {
            builder.setPriority(PRIORITY_DEFAULT);
        }
        builder.setContent(getRemoteView());
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        getNotificationManager().notify(3, notification);
    }

    private RemoteViews getRemoteView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_custom_notifition);
        remoteViews.setTextViewText(R.id.notification_title, "我的测试");
        remoteViews.setTextViewText(R.id.notification_content, "这是一个测试");
        remoteViews.setOnClickPendingIntent(R.id.rlnotification, getPendingIntent(13));
        return remoteViews;
    }

    private PendingIntent getPendingIntent(int what) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, what, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "define_id")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon_person)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_person))
                .setAutoCancel(true);
        return builder;
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    private void initNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
    }


    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    public void sendChatMsg() {

        Intent intentp = new Intent();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intentp,0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder chat = new NotificationCompat.Builder(this, "chat");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        } else {
            chat.setPriority(Notification.PRIORITY_DEFAULT);
        }
        Notification notification = chat
                .setTicker("您有新短消息")
                .setContentTitle("收到一条聊天消息")
                .setContentText("今天中午吃什么？")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis()) //设置通知时间
                .setDefaults(Notification.DEFAULT_SOUND)//设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                .setSmallIcon(R.drawable.icon_person)    //设置小图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_person)) //设置大图标
                .setAutoCancel(true)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(1, notification);
    }

    public void sendSubscribeMsg() {

        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent,0);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder subscribe;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            subscribe = new NotificationCompat.Builder(getApplicationContext(), "subscribe");
        } else {
            subscribe = new NotificationCompat.Builder(getApplicationContext(), "subscribe");
            subscribe.setPriority(PRIORITY_DEFAULT);
        }
        Notification notification = subscribe
                .setTicker("您有新短消息")
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon_person)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_person))
                .setAutoCancel(true)
                .setNumber(2)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(2, notification);
    }


}
