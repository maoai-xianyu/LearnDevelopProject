package com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author zhangkun
 * @time 2020-06-01 09:40
 * @Description
 */
public abstract class BottomTabItem {

    // 布局id  Context 最好采用builder设计模式

    private View mTabView;
    private Context mContext;
    private int mLayoutId;
    private SparseArray<WeakReference<View>> mViews;

    public BottomTabItem(Context context, int layoutId) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mViews = new SparseArray<>();
    }


    protected View getTabView() {
        if (mTabView == null) {
            mTabView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
            initLayout();
        }
        return mTabView;
    }

    protected abstract void initLayout();


    public <T extends View> T findViewById(int viewId) {
        WeakReference<View> viewWeakReference = mViews.get(viewId);
        View view = viewWeakReference != null ? viewWeakReference.get() : null;
        if (view == null) {
            view = mTabView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;

    }

    /**
     * 是否选中当前条目
     *
     * @param selected
     */
    abstract void setSelected(boolean selected);


}
