package com.mao.cn.learnDevelopProject.utils;

import android.content.Context;

/**
 * @author zhangkun
 * @time 2020-03-06 13:45
 */
public class CommUtils {
    private static CommUtils instance = null;
    private Context mContext;

    private CommUtils(Context context) {
        mContext = context;
    }

    public static CommUtils getInstance(Context context) {
        if (instance == null) {
            instance = new CommUtils(context);
        }
        return instance;
    }
}
