package com.mao.cn.learnDevelopProject.ui.activity.androidxf

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.mao.cn.learnDevelopProject.model.TestBean

class DiffCallBackNew(private val mOldDatas: List<TestBean>?, //看名字
                      private val mNewDatas: List<TestBean>?) : DiffUtil.Callback() {
    //老数据集size
    override fun getOldListSize(): Int {
        return mOldDatas?.size ?: 0
    }

    //新数据集size
    override fun getNewListSize(): Int {
        return mNewDatas?.size ?: 0
    }

    /**
     * Called by the DiffUtil to decide whether two object represent the same Item.
     * 被DiffUtil调用，用来判断 两个对象是否是相同的Item。
     * For example, if your items have unique ids, this method should check their id equality.
     * 例如，如果你的Item有唯一的id字段，这个方法就 判断id是否相等。
     * 本例判断name字段是否一致
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return True if the two items represent the same object or false if they are different.
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldDatas!![oldItemPosition].name == mNewDatas!![newItemPosition].name
    }

    /**
     * Called by the DiffUtil when it wants to check whether two items have the same data.
     * 被DiffUtil调用，用来检查 两个item是否含有相同的数据
     * DiffUtil uses this information to detect if the contents of an item has changed.
     * DiffUtil用返回的信息（true false）来检测当前item的内容是否发生了变化
     * DiffUtil uses this method to check equality instead of [Object.equals]
     * DiffUtil 用这个方法替代equals方法去检查是否相等。
     * so that you can change its behavior depending on your UI.
     * 所以你可以根据你的UI去改变它的返回值
     * For example, if you are using DiffUtil with a
     * [RecyclerView.Adapter][android.support.v7.widget.RecyclerView.Adapter], you should
     * return whether the items' visual representations are the same.
     * 例如，如果你用RecyclerView.Adapter 配合DiffUtil使用，你需要返回Item的视觉表现是否相同。
     * This method is called only if [.areItemsTheSame] returns
     * `true` for these items.
     * 这个方法仅仅在areItemsTheSame()返回true时，才调用。
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list which replaces the
     * oldItem
     * @return True if the contents of the items are the same or false if they are different.
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val beanOld = mOldDatas!![oldItemPosition]
        val beanNew = mNewDatas!![newItemPosition]
        if (beanOld.desc != beanNew.desc) {
            return false //如果有内容不同，就返回false
        }
        return beanOld.pic == beanNew.pic
        //默认两个data内容是相同的
    }

    /**
     * When [.areItemsTheSame] returns `true` for two items and
     * [.areContentsTheSame] returns false for them, DiffUtil
     * calls this method to get a payload about the change.
     *
     *
     * 当[.areItemsTheSame] 返回true，且[.areContentsTheSame] 返回false时，DiffUtils会回调此方法，
     * 去得到这个Item（有哪些）改变的payload。
     *
     *
     * For example, if you are using DiffUtil with [RecyclerView], you can return the
     * particular field that changed in the item and your
     * [ItemAnimator][android.support.v7.widget.RecyclerView.ItemAnimator] can use that
     * information to run the correct animation.
     *
     *
     * 例如，如果你用RecyclerView配合DiffUtils，你可以返回  这个Item改变的那些字段，
     * [ItemAnimator][android.support.v7.widget.RecyclerView.ItemAnimator] 可以用那些信息去执行正确的动画
     *
     *
     * Default implementation returns `null`.\
     * 默认的实现是返回null
     *
     * @param oldItemPosition The position of the item in the old list
     * @param newItemPosition The position of the item in the new list
     * @return A payload object that represents the change between the two items.
     * 返回 一个 代表着新老item的改变内容的 payload对象，
     */
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //实现这个方法 就能成为文艺青年中的文艺青年
        // 定向刷新中的部分更新
        // 效率最高
        //只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要）
        val oldBean = mOldDatas!![oldItemPosition]
        val newBean = mNewDatas!![newItemPosition]

        //这里就不用比较核心字段了,一定相等
        val payload = Bundle()
        if (oldBean.desc != newBean.desc) {
            payload.putString("KEY_DESC", newBean.desc)
        }
        if (oldBean.pic != newBean.pic) {
            payload.putInt("KEY_PIC", newBean.pic)
        }
        return if (payload.size() == 0) null else payload
    }
}