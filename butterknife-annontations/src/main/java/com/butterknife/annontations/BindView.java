package com.butterknife.annontations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangkun
 * @time 2020-03-22 18:59
 *
 * 用来注入View
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS) // 编译时注解
public @interface BindView {
}
