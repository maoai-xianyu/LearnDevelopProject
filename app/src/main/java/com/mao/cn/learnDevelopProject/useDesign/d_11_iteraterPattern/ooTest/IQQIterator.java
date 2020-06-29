package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.ooTest;

import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-28 16:17
 * @Description
 */
public class IQQIterator implements Iterator<UserInfo> {

    private List<UserInfo> mUserInfos;
    private int index = 0;

    public IQQIterator(List<UserInfo> userInfos) {
        mUserInfos = userInfos;
    }

    @Override
    public UserInfo next() {
        return mUserInfos.get(index++);
    }

    @Override
    public boolean hasNext() {
        return index < mUserInfos.size();
    }
}
