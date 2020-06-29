package com.mao.cn.learnDevelopProject.useDesign.d_12_dutychainPattern.handler;

/**
 * @author zhangkun
 * @time 2020-06-01 21:58
 * @Description 责任链设计模式-抽象处理者接口
 */
public interface IUserSystemHandler<T extends AbsUserSystemHandler> {
    public void nextHandler(T systemHandler);

}
