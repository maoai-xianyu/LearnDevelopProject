package com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler;

/**
 * @author zhangkun
 * @time 2020-06-01 22:03
 * @Description 校验用的处理 接口
 */
public interface IUserSystem {

    public UserInfo getUserInfo(String name, String psd);
}
