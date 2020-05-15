package com.mao.cn.learnDevelopProject.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mao.cn.learnDevelopProject.utils.tools.LogU


/**
 *
 * @author zhangkun
 * @time 2020-04-02 11:04
 */
class TranslationBehavior : FloatingActionButton.Behavior {


    private var mIsShow: Boolean = false

    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    // 关注垂直滚动，而且向上的时候是出来，向下的时候是隐藏
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        // 垂直滚动
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }


    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int, consumed: IntArray) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed)
        // 而且向上的时候是出来,向下隐藏
        LogU.d("dyConsumed  没有到底没有到头 向下为负  向上为正  $dyConsumed 同时 dyUnconsumed 为 0   当到底到头 dyConsumed 为0 之后触发  dyUnconsumed 向下为负数 向上为正 $dyUnconsumed")
        if (dyConsumed > 0) {
            // 上滑动 是隐藏 添加标志位
            if (!mIsShow){
                val layoutParams: CoordinatorLayout.LayoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
                val translateY = layoutParams.bottomMargin + child.measuredHeight
                child.animate().translationY(translateY.toFloat()).setInterpolator(OvershootInterpolator()).setDuration(500).start()
            }

        } else {
            // 往下滑动
            if (mIsShow){
                child.animate().translationY(0.toFloat()).setInterpolator(OvershootInterpolator()).setDuration(500).start()
            }
        }
        mIsShow = !mIsShow
    }

}