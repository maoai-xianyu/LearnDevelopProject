package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-28 16:21
 * @Description 容器的接口
 */
public interface Aggregate<T> {

    Iterator<T> iterator();
}
