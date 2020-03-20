package com.mao.cn.learnDevelopProject.ui.activity.aspectJ;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangkun
 * @time 2020-03-19 16:48
 * 标记切点  注解
 */
@Target(ElementType.METHOD) // target 放在哪个位置  TYPE 是类 METHOD 是方法 FIELD 是成员变量
@Retention(RetentionPolicy.RUNTIME) // RUNTIME 运行时 xUitls  CLASS 编译时期 ButterKnife  SOURCE 代表资源
public @interface CheckNet { // @interface 注解
}
