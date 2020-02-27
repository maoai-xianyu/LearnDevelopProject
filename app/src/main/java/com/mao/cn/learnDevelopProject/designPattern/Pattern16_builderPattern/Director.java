package com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern;

import com.mao.cn.learnDevelopProject.designPattern.Pattern16_builderPattern.builder.AbsBuilder;

/**
 * @author zhangkun
 * @time 2020-02-27 14:23
 */
public class  Director {
    private AbsBuilder mAbsBuilder;

    public Director(AbsBuilder absBuilder) {
        mAbsBuilder = absBuilder;
    }

    public void setAbsBuilder(AbsBuilder absBuilder) {
        mAbsBuilder = absBuilder;
    }

    public void construct() {
        mAbsBuilder.buildVacation();
        mAbsBuilder.getVacation().showInfo();
    }
}
