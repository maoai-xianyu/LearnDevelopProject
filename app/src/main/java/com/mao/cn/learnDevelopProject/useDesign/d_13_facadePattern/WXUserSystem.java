package com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern;

import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.AbsUserSystemHandler;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.UserInfo;

/**
 * @author zhangkun
 * @time 2020-05-28 15:53
 * @Description
 */
public class WXUserSystem extends AbsUserSystemHandler {

    private UserInfo[] mUserInfos;

    public WXUserSystem() {
        mUserInfos = new UserInfo[3];
        mUserInfos[0] = new UserInfo("wx大南", "123456", "1", "男");
        mUserInfos[1] = new UserInfo("wx大丹", "123456", "2", "男");
        mUserInfos[2] = new UserInfo("wx大二", "123456", "3", "男");
    }


    @Override
    public UserInfo getUserInfo(String name, String psd) {
        for (UserInfo next : mUserInfos) {
            if (next.userName.equals(name) && next.userPsd.equals(psd)) {
                return next;
            }
        }

        AbsUserSystemHandler nextHandler = getNextHandler();
        if (nextHandler != null) {
            return nextHandler.getUserInfo(name, psd);
        }

        return null;
    }

}
