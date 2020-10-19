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
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

public class RadiusBackgroundSpan extends ReplacementSpan {
    private int fontSize = -1;
    private boolean isSp = true;
    private int margin;
    private int padding;
    private int radius;
    private int textColor;
    private int bgColor;

    public RadiusBackgroundSpan(int fontSize, int margin, int radius, int textColor, int bgColor) {
        this.fontSize = fontSize;
        this.margin = margin;
        this.radius = radius;
        this.textColor = textColor;
        this.bgColor = bgColor;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        Paint newPaint = getCustomTextPaint(paint);
        return (int) newPaint.measureText(text, start, end) + margin * 2;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int
            bottom, @NonNull Paint paint) {
        Paint newPaint = getCustomTextPaint(paint);

        int textWidth = (int) newPaint.measureText(text, start, end);

        RectF rect = new RectF();
        rect.top = top + margin;
        rect.bottom = bottom - margin;
        rect.left = (int) (x + margin);
        rect.right = rect.left + textWidth + margin;

        paint.setColor(bgColor);
        canvas.drawRoundRect(rect, radius, radius, paint);
        LogU.d("ssssssss");

        newPaint.setColor(textColor);
        Paint.FontMetrics fontMetrics = newPaint.getFontMetrics();
        int offsetX = (int) ((rect.right - rect.left - textWidth) / 2) + margin;
        int offsetY = (int) ((y + fontMetrics.ascent + y + fontMetrics.descent) / 2 - (top + bottom) / 2);
        canvas.drawText(text, start, end, x + offsetX, y - offsetY, newPaint);

    }

    private TextPaint getCustomTextPaint(Paint srcPaint) {
        TextPaint textPaint = new TextPaint(srcPaint);
        /*if (fontSize != -1) {
            textPaint.setTextSize(isSp ? fontSize * textPaint.density : fontSize);
        }*/
        textPaint.setTextSize(sp2px(fontSize));
        return textPaint;
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp,
                LearnDevelopApplication.getInstance().getResources().getDisplayMetrics());
    }
}

