package com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern;


import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.AbsUserSystemHandler;
import com.mao.cn.learnDevelopProject.useDesign.d_13_facadePattern.handler.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-28 15:52
 * @Description
 */
public class NYUserSystem extends AbsUserSystemHandler {

    private List<UserInfo> mUserInfos;

    public NYUserSystem() {
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("ny大南","123456","1","男"));
        mUserInfos.add(new UserInfo("ny大丹","123456","2","男"));
        mUserInfos.add(new UserInfo("ny大二","123456","13","男"));
    }


    @Override
    public UserInfo getUserInfo(String name, String psd) {
        for (UserInfo next : mUserInfos) {
            if (next.userName.equals(name) && next.userPsd.equals(psd)) {
                return next;
            }
        }

        AbsUserSystemHandler nextHandler = getNextHandler();
        if (nextHandler != null){
            return nextHandler.getUserInfo(name,psd);
        }

        return null;
    }
}
