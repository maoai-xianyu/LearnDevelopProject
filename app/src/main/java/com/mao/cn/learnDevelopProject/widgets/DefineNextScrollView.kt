package com.mao.cn.learnDevelopProject.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import com.mao.cn.learnDevelopProject.utils.tools.LogU

/**
 *
 * @author zhangkun
 * @time 2020-04-01 23:51
 */
class DefineNextScrollView : NestedScrollView {

    var listener: DefineNextScrollChangeListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        LogU.d(" l $l  t $t  oldl $oldl  oldt  $oldt")
        listener?.let {

            // 获取屏幕的高
            val heightPixels = context.resources.displayMetrics.heightPixels
            if (t <= heightPixels / 3f) {
                it.onAlpha((t / (heightPixels / 3f)))
            } else {
                it.onAlpha(1f)
            }
        }

    }

    interface DefineNextScrollChangeListener {
        fun onAlpha(alpha: Float)
    }
}