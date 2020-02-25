package com.mao.cn.learnDevelopProject.designPattern.Pattern13_proxyPattern.dyn;

/**
 * @author zhangkun
 * @time 2020-02-24 16:11
 */
public interface PersonBean {
    String getName();

    String getGender();

    String getInterests();

    int getHotOrNotRating();

    void setName(String name);

    void setGender(String gender);

    void setInterests(String interests);

    void setHotOrNotRating(int rating);
}
