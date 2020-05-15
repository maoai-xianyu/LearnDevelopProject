package com.mao.cn.learnDevelopProject.useDesign.d_01_SinglePattern.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * @author zhangkun
 * @time 2020-05-15 11:23
 * @Description activity栈的管理
 */
public class ActivityManager {

    private static volatile ActivityManager mInstance;

    // 删除和添加比较多用
    private Stack<Activity> mActivities;

    private ActivityManager() {
        mActivities = new Stack<>();
    }

    public static ActivityManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityManager();
                }
            }
        }
        return mInstance;
    }


    /**
     * 添加
     *
     * @param activity
     */
    public void attach(Activity activity) {
        mActivities.add(activity);

    }

    /**
     * 移除解绑 - 防止内存泄漏
     */
    public void detach(Activity detachActivity) {
        //一边移除一边循环会出问题，如何做？
        // 方案一：如果是 arrayList 用的  iterator

        // 方案二： EventBus的源码

        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity == detachActivity) {
                mActivities.remove(i);
                i--;
                size--;
            }
        }
    }


    /**
     * 关闭activity
     *
     * @param finishActivity
     */
    public void finish(Activity finishActivity) {
        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity == finishActivity) {
                mActivities.remove(i);
                activity.finish();
                i--;
                size--;
            }
        }

    }

    /**
     * 根据Activity的类名关闭 Activity
     *
     * @param activityClass
     */
    public void finish(Class<? extends Activity> activityClass) {

        int size = mActivities.size();
        for (int i = 0; i < size; i++) {
            Activity activity = mActivities.get(i);
            if (activity.getClass().getCanonicalName().equals(activityClass.getCanonicalName())) {
                mActivities.remove(i);
                activity.finish();
                i--;
                size--;
            }
        }

    }


    /**
     * 退出整个应用
     */
    public void exitApplication() {
        try {
            int size = mActivities.size();
            for (int i = 0; i < size; i++) {
                Activity activity = mActivities.get(i);
                mActivities.remove(i);
                activity.finish();
                i--;
                size--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }

    }


    /**
     * 获取当前的 Activity , 可以在 service 中进行弹框
     *
     * @return
     */
    public Activity currentActivity() {
        return mActivities.lastElement();
    }


}
