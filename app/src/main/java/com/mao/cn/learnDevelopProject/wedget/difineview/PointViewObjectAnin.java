package com.mao.cn.learnDevelopProject.wedget.difineview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.mao.cn.learnDevelopProject.model.Point;

/**
 * author:  zhangkun .
 * date:    on 2017/9/14.
 */

public class PointViewObjectAnin extends View {

    private Point mCurPoint = new Point(100);

    public PointViewObjectAnin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurPoint != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(300, 300, mCurPoint.getRadius(), paint);
        }
    }

    /**
     * 第一点，这个set函数所对应的属性应该是pointRadius或者PointRadius。
     * 前面我们已经讲了第一个字母大小写无所谓，后面的字母必须保持与set函数完全一致。
     * 第二点，在setPointRadius中，先将当前动画传过来的值保存到mPoint中，做为当前圆形的半径。然后强制界面刷新
     *
     * @param radius
     */
    public void setPointRadius(int radius) {
        mCurPoint.setRadius(radius);
        invalidate();
    }

    public int getPointRadius() {
        return 50;
    }
}
