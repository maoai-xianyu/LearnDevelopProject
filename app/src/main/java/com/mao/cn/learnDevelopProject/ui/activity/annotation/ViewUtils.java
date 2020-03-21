package com.mao.cn.learnDevelopProject.ui.activity.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * @author zhangkun
 * @time 2020-03-20 23:33
 */
public class ViewUtils {

    public static void inject(Activity activity) {
        // 1. 获取所有属性
        Field[] declaredFields = activity.getClass().getDeclaredFields();
        // 2. 过滤关于 ViewById 属性
        for (Field field : declaredFields) {

            ViewById viewById = field.getAnnotation(ViewById.class);
            if (viewById != null){
                // 3. findViewById()
                View view = activity.findViewById(viewById.value());
                // 4. 反射注入
                field.setAccessible(true);
                try {
                    // activity 属性所在类，view 代表是属性的值
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
