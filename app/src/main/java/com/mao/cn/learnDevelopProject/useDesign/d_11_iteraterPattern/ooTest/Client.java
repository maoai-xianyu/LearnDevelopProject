package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-28 15:47
 * @Description
 */
public class Client {

    public static void main(String[] args) {

        // 根据用户名和密码查询用户信息，有就登录成功，没有就登录失败

        WXUserSystem wxUserSystem = new WXUserSystem();

        UserInfo userInfo = getUserInfo("wx大南", "123456", wxUserSystem.iterator());
        if (userInfo == null) {
            QQUserSystem qqUserSystem = new QQUserSystem();
            userInfo = getUserInfo("qq大南", "123456", qqUserSystem.iterator());
        }
        if (userInfo == null) {
            System.out.println("没有用户");
        } else {
            System.out.println("登录成功");
        }


    }

    private static UserInfo getUserInfo(String name, String psd, Iterator<UserInfo> iterator) {
        while (iterator.hasNext()) {
            UserInfo next = iterator.next();
            if (next.userName.equals(name) && next.userPsd.equals(psd)) {
                return next;
            }
        }

        return null;
    }

}
