package com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.facade;

import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.NYUserSystem;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.QQUserSystem;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.WXUserSystem;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.AbsUserSystemHandler;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.IUserSystem;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.UserInfo;

/**
 * @author zhangkun
 * @time 2020-06-01 23:01
 * @Description  外观设计模式 -- 易于使用的高层次的接口
 */
public class UserSystemFacade implements IUserSystem {

    private final AbsUserSystemHandler mWxUserSystem;

    public UserSystemFacade() {

        mWxUserSystem = new WXUserSystem();
        QQUserSystem qqUserSystem = new QQUserSystem();
        NYUserSystem nyUserSystem = new NYUserSystem();
        // 系统很多---> 用责任链模式

        mWxUserSystem.nextHandler(qqUserSystem);
        qqUserSystem.nextHandler(nyUserSystem);
    }

    @Override
    public UserInfo getUserInfo(String name, String psd) {
        return mWxUserSystem.getUserInfo(name,psd);
    }
}
