package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.iterator;

import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-28 15:47
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 根据用户名和密码查询用户信息，有就登录成功，没有就登录失败
        UserInfo userInfo = getWXUserInfo("wx大南","123456");
        if (userInfo == null){
            userInfo = getQQUserInfo("qq大南","123456");
        }
        if (userInfo == null){
            System.out.println("没有用户");
        }else {
            System.out.println("登录成功");
        }


    }

    private static UserInfo getQQUserInfo(String name, String psd) {
        System.out.println("qq查询");
        QQUserSystem wxUserSystem = new QQUserSystem();
        List<UserInfo> userInfos = wxUserSystem.getUserInfos();

        for (UserInfo userInfo : userInfos) {
            if (userInfo.userName.equals(name) && userInfo.userPsd.equals(psd)){
                return userInfo;
            }
        }

        return null;
    }

    private static UserInfo getWXUserInfo(String name, String psd) {
        System.out.println("wx查询");
        WXUserSystem wxUserSystem = new WXUserSystem();
        UserInfo[] userInfos = wxUserSystem.getUserInfos();

        for (UserInfo userInfo : userInfos) {
            if (userInfo.userName.equals(name) && userInfo.userPsd.equals(psd)){
                return userInfo;
            }
        }

        return null;
    }
}
