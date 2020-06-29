package com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern;

import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.facade.UserSystemFacade;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.UserInfo;

/**
 * @author zhangkun
 * @time 2020-05-28 15:47
 * @Description  外观模式
 */
public class Client {

    public static void main(String[] args) {

        UserSystemFacade userSystemFacade = new UserSystemFacade();
        UserInfo userInfo = userSystemFacade.getUserInfo("ny大南", "123456");
        if (userInfo == null) {
            System.out.println("没有用户"+ null);
        } else {
            System.out.println("登录成功"+userInfo.toString());
        }


    }


}
