package com.mao.cn.learnDevelopProject.wedget.spannerString;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class ClickableColorSpan extends ClickableSpan implements View.OnClickListener {
    private View.OnClickListener mListener;

    public ClickableColorSpan() {
    }
    public void setmListener(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        mListener.onClick(v);
    }
    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);    //去除超链接的下划线
    }
}
