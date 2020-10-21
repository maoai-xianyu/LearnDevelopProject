package com.mao.cn.learnDevelopProject.ui.activity.tabhost;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mao.cn.learnDevelopProject.LearnDevelopApplication;

public class RadiusBackgroundSpan extends ReplacementSpan {
    private int fontSize;
    private int margin;
    private int radius;
    private int textColor;
    private int bgColor;
    private int mSize;

    public static final int STYLE_FILL = 0;//填充
    public static final int STYLE_STROCK = 1;//扫边。扫边颜色默认和字体颜色一致
    private int mStyle;

    public RadiusBackgroundSpan(int fontSize, int margin, int radius, int textColor, int bgColor) {
        this(fontSize, margin, radius, textColor, bgColor, STYLE_FILL);
    }

    public RadiusBackgroundSpan(int fontSize, int margin, int radius, int textColor, int bgColor, int style) {
        this.fontSize = fontSize;
        this.margin = margin;
        this.radius = radius;
        this.textColor = textColor;
        this.bgColor = bgColor;
        this.mStyle = style;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        Paint newPaint = getCustomTextPaint(paint);
        mSize = (int) newPaint.measureText(text, start, end) + margin * 2;
        return mSize;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int
            bottom, @NonNull Paint paint) {
        Paint newPaint = getCustomTextPaint(paint);

        RectF rect = new RectF(x, y + paint.ascent(), x + mSize, y + paint.descent());


        if (mStyle == STYLE_STROCK) {
            paint.setColor(textColor);
            paint.setStyle(Paint.Style.STROKE);
        } else {
            paint.setColor(bgColor);
            paint.setStyle(Paint.Style.FILL);
        }
        canvas.drawRoundRect(rect, radius, radius, paint);

        newPaint.setAntiAlias(true);
        newPaint.setColor(textColor);
        //int offsetX = (int) ((rect.right - rect.left - textWidth) / 2) + margin;
        //int offsetX = (int) (mSize - paint.measureText(text.subSequence(start, end).toString()));
        //int offsetX = (int) (mSize - paint.measureText(text, start, end));
        int textWidth = (int) newPaint.measureText(text, start, end);
        int offsetX = (int) ((rect.right - rect.left - textWidth) / 2);
        canvas.drawText(text, start, end, x + offsetX, y, newPaint);

    }

    private TextPaint getCustomTextPaint(Paint srcPaint) {
        TextPaint textPaint = new TextPaint(srcPaint);
        textPaint.setTextSize(sp2px(fontSize));
        return textPaint;
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp,
                LearnDevelopApplication.getInstance().getResources().getDisplayMetrics());
    }
}

