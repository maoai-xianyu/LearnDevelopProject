package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-28 16:17
 * @Description
 */
public class IWXIterator implements Iterator<UserInfo>{

    private UserInfo[] mUserInfos;
    private int index = 0;

    public IWXIterator(UserInfo[] userInfos) {
        mUserInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return mUserInfos[index++];
    }

    @Override
    public boolean hasNext() {
        return index<mUserInfos.length;
    }
}
