package com.butterknife;

import android.app.Activity;

import java.lang.reflect.Constructor;

/**
 * @author zhangkun
 * @time 2020-03-22 22:29
 */
public class DefineButterKnife {


    public static DefineUnbinder bind(Activity activity) {
        //  xxx_ViewBinding viewBinding = new xxx_ViewBinding(this);
        try {
            Class<? extends DefineUnbinder> unbinderClassName = (Class<? extends DefineUnbinder>)
                    Class.forName(activity.getClass().getName() + "_ViewBinding");

            // 获取构造函数
            Constructor<? extends DefineUnbinder> unbinderConstructor = unbinderClassName.getDeclaredConstructor(activity.getClass());

            DefineUnbinder defineUnbinder = unbinderConstructor.newInstance(activity);
            return defineUnbinder;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return DefineUnbinder.EMPTY;

    }
}
