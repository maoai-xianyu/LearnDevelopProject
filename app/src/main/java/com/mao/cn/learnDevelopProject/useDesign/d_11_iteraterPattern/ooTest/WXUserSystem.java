package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.ooTest;

/**
 * @author zhangkun
 * @time 2020-05-28 15:53
 * @Description
 */
public class WXUserSystem implements Aggregate<UserInfo> {

    private UserInfo[] mUserInfos;

    public WXUserSystem() {
        mUserInfos = new UserInfo[3];
        mUserInfos[0] = new UserInfo("wx大南", "123456", "1", "男");
        mUserInfos[1] = new UserInfo("wx大丹", "123456", "2", "男");
        mUserInfos[2] = new UserInfo("wx大二", "123456", "3", "男");
    }


    @Override
    public Iterator<UserInfo> iterator() {
        return new IWXIterator(mUserInfos);
    }
}
