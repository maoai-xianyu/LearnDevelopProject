package com.mao.cn.learnDevelopProject.wedget.spannerString;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by john
 * Date 2015/9/24
 * Time 10:30
 */
public class WeifaClickSpan extends ClickableSpan{
    private OnTextviewClickListener mListener;

    public WeifaClickSpan(OnTextviewClickListener listener){
        this.mListener = listener;
    }

    @Override
    public void onClick(View view) {
        mListener.clickTextView();
    }



    @Override
    public void updateDrawState(TextPaint ds) {
        mListener.setStyle(ds);
    }
}
