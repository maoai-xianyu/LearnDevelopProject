package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-28 15:52
 * @Description
 */
public class QQUserSystem {

    private List<UserInfo> mUserInfos;

    public QQUserSystem() {
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("qq大南","123456","1","男"));
        mUserInfos.add(new UserInfo("qq大丹","123456","2","男"));
        mUserInfos.add(new UserInfo("qq大二","123456","13","男"));
    }


    public List<UserInfo> getUserInfos() {
        return mUserInfos;
    }
}
