package com.mao.cn.learnDevelopProject.wedget.difineview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * author:  zhangkun .
 * date:    on 2017/9/14.
 */

public class DefineTextView extends AppCompatTextView {


    public DefineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCharText(Character character) {
        setText(String.valueOf(character));
    }
}
