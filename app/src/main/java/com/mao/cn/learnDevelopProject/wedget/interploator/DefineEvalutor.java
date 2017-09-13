package com.mao.cn.learnDevelopProject.wedget.interploator;

import android.animation.TypeEvaluator;

/**
 * author:  zhangkun .
 * date:    on 2017/9/13.
 */

/**
 * 只有定义动画时的数值类型与Evalutor的返回值类型一样时，才能使用这个Evalutor
 */
public class DefineEvalutor implements TypeEvaluator<Integer>{
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        int startInt = startValue;
        return (int)(200+startInt + fraction * (endValue - startInt));
    }
}
