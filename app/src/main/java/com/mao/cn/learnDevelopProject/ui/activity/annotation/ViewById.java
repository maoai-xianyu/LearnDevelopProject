package com.mao.cn.learnDevelopProject.ui.activity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangkun
 * @time 2020-03-20 23:19
 */
@Target(ElementType.FIELD) // @Target 放在那里，那里可以使用  FIELD 是属性  METHOD 是方法  TYPE 是 类上
@Retention(RetentionPolicy.RUNTIME) // 什么时候起作用  RUNTIME 运行时(程序运行中） CLASS 编译时(打包的时候) SOURCE 编程阶段
public @interface ViewById { // @interface 代表注解
    int value();
}
