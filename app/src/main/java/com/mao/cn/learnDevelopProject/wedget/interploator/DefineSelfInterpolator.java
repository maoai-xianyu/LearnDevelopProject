package com.mao.cn.learnDevelopProject.wedget.interploator;

import android.view.animation.Interpolator;

/**
 * author:  zhangkun .
 * date:    on 2017/9/13.
 */

public class DefineSelfInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return 1 - input;
    }
}
