package com.mao.cn.learnDevelopProject.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

/**
 *
 * @author zhangkun
 * @time 2020-04-01 23:51
 */
class DefineScrollView : ScrollView {

    var listener: DefineScrollChangeListener? = null


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr, 0)

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        listener?.onScrollChanged(l, t, oldl, oldt)

    }

    interface DefineScrollChangeListener {
        fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int)
    }
}