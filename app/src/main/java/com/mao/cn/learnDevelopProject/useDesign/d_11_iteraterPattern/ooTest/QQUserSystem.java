package com.mao.cn.learnDevelopProject.useDesign.d_11_iteraterPattern.ooTest;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-28 15:52
 * @Description
 */
public class QQUserSystem implements Aggregate<UserInfo>{

    private List<UserInfo> mUserInfos;

    public QQUserSystem() {
        mUserInfos = new ArrayList<>();
        mUserInfos.add(new UserInfo("qq大南","123456","1","男"));
        mUserInfos.add(new UserInfo("qq大丹","123456","2","男"));
        mUserInfos.add(new UserInfo("qq大二","123456","13","男"));
    }

    @Override
    public Iterator<UserInfo> iterator() {
        return new IQQIterator(mUserInfos);
    }
}
