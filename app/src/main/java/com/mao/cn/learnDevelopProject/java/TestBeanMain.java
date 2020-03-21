package com.mao.cn.learnDevelopProject.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zhangkun
 * @time 2020-03-20 22:23
 */
public class TestBeanMain {

    public static void main(String[] args) {

        try {

            // 创建对象，很多系统的类，都被系统隐藏了。或者说只有系统可以直接new,可以通过
            // 反射创建对象 newInstance() 调用的是无参
            TestBean testBean = TestBean.class.newInstance();
            testBean.setName("test");
            testBean.sysName();


            // getDeclaredConstructor 从所有的构造方法里面找
            // getConstructor 从公共构造方法里面找
            Constructor<TestBean> constructor = TestBean.class.getDeclaredConstructor(String.class);
            constructor.setAccessible(true); // 设置权限
            TestBean test = constructor.newInstance("晴天不改");
            test.sysName();


            // 获取方法
            Method method = TestBean.class.getDeclaredMethod("sayThisName");
            method.setAccessible(true);
            method.invoke(test);

            // 获取属性

            Field field = TestBean.class.getDeclaredField("name");
            field.setAccessible(true);
            String str = (String) field.get(test);
            System.out.println("str " + str);


            // 获取安卓
            /*Class clazz = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThread = clazz.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThread.setAccessible(true);
            Object o = sCurrentActivityThread.get(null);
            System.out.println("0 "+o);*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
