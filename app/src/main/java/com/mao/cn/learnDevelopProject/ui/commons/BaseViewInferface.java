package com.mao.cn.learnDevelopProject.ui.commons;

import com.mao.cn.learnDevelopProject.common.CommViewInterface;
import com.mao.cn.learnDevelopProject.converter.RetrofitError;

/**
 * Created by zhangkun on 2017/6/9.
 */

public interface BaseViewInferface extends CommViewInterface {


    void interError(RetrofitError error);

    void interError(Throwable throwable);
}
