// +----------------------------------------------------------------------
// | CreateTime: 2017/2/8 
// +----------------------------------------------------------------------
// | Author:     xab(http://www.xueyong.net.cn)
// +----------------------------------------------------------------------
// | CopyRight:  http://www.boxfish.cn
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhangkun on 2017/6/8.
 */
public abstract class CommApplication extends Application {
    private static CommApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.beforeCreate();
        super.onCreate();
        this.initApplication();
        this.afterOnCreate();
    }

    protected abstract void initApplication();

    protected abstract void beforeCreate();

    protected abstract void afterOnCreate();

    public static Context context() {
        return instance.getApplicationContext();
    }
}
