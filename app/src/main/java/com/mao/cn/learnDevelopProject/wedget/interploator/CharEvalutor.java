package com.mao.cn.learnDevelopProject.wedget.interploator;

import android.animation.TypeEvaluator;

/**
 * author:  zhangkun .
 * date:    on 2017/9/13.
 */

/**
 * 在ASCII码表中，每个字符都是有数字跟他一一对应的，字母A到字母Z之间的所有字母对应的数字区间为65到90
 */
public class CharEvalutor implements TypeEvaluator<Character> {

    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt + fraction * (endInt - startInt));
        return (char) curInt;
    }
}
