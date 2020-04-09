package com.mao.cn.learnDevelopProject.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * @author zhangkun
 * @time 2020-04-01 16:06
 */
public class StatusBarUtil {


    /**
     * 为我们的Activity 的状态栏设置颜色
     *
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity, int color) {
        // 5.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 直接调用系统的方法
            activity.getWindow().setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4 -5.0之前 采用一个技巧，首先把它弄成全屏，然后在状态栏的部分加一个布局
            //activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); // 全屏 都不在
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 电量时间网络还在
            // 在状态栏的部分加一个布局 setContentView 源码分析，在 DecorView 添加
            View view = new View(activity);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity)));
            view.setBackgroundColor(color);
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(view);

            // 获取根布局
            ViewGroup contentView = activity.findViewById(android.R.id.content);
            // 方式一
            contentView.setPadding(0, getStatusBarHeight(activity), 0, 0);
            // 当前activity布局
            View childAt = contentView.getChildAt(0);
            // 方式二
            //childAt.setFitsSystemWindows(true);
            // 方式三
            //childAt.setPadding(0,getStatusBarHeight(activity),0,0);


        }


    }

    // 获取状态栏的高度
    public static int getStatusBarHeight(Context activity) {
        // 插件式换肤 如何获取资源的，先获取资源id,根据id获取资源 源码
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("getStatusBarHeight", result + "");
        return result;
    }


    /**
     * 设置  activity 全屏
     *
     * @param activity
     */
    public static void setStatusBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 直接调用系统的方法
            View decorView = activity.getWindow().getDecorView();
            int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(options);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置全屏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //
        }
    }


}
