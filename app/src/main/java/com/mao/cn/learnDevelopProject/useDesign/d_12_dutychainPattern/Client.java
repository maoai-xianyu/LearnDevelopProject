package com.mao.cn.learnDevelopProject.useDesign.d_12_dutychainPattern;

import com.mao.cn.learnDevelopProject.useDesign.d_12_dutychainPattern.handler.UserInfo;

/**
 * @author zhangkun
 * @time 2020-05-28 15:47
 * @Description  责任链模式
 */
public class Client {

    public static void main(String[] args) {

        WXUserSystem wxUserSystem = new WXUserSystem();
        QQUserSystem qqUserSystem = new QQUserSystem();
        NYUserSystem nyUserSystem = new NYUserSystem();
        // 系统很多---> 用责任链模式

        wxUserSystem.nextHandler(qqUserSystem);
        qqUserSystem.nextHandler(nyUserSystem);


        UserInfo userInfo = wxUserSystem.getUserInfo("ny大南", "123456");
        if (userInfo == null) {
            System.out.println("没有用户"+ null);
        } else {
            System.out.println("登录成功"+userInfo.toString());
        }


    }


}
