package com.mao.cn.learnDevelopProject.useDesign.d_02_navigationbar.navigationBar;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangkun
 * @time 2020-05-16 15:31
 * @Description 导航栏的基类
 */
public class AbsNavigationBar<B extends AbsNavigationBar.Builder> implements INavigation {

    private B mBuilder;
    private View mNavigationBar;
    private SparseArray<WeakReference<View>> mViews;

    protected AbsNavigationBar(B builder) {
        this.mBuilder = builder;
        this.mViews = new SparseArray<>();
        createNavigationBar();
    }

    @Override
    public void createNavigationBar() {
        mNavigationBar = LayoutInflater.from(mBuilder.mContext).inflate(mBuilder.mLayout,
                mBuilder.mParent, false);
        // 添加
        attachParent(mNavigationBar, mBuilder.mParent);
    }

    /**
     * 将 NavigationBar 添加到父布局
     *
     * @param navigationBar
     * @param parent
     */
    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar, 0);
        // 绑定参数
        attachNavigationParams();
    }


    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
        Map<Integer, CharSequence> textMaps = mBuilder.mTextMaps;
        for (Map.Entry<Integer, CharSequence> entry : textMaps.entrySet()) {
            TextView textView = findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }

        Map<Integer, View.OnClickListener> mClickListenerMaps = mBuilder.mClickListenerMaps;
        for (Map.Entry<Integer, View.OnClickListener> entry : mClickListenerMaps.entrySet()) {
            View view = findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }


    }

    public  <T extends View> T findViewById(int viewId) {
        WeakReference<View> viewWeakReference = mViews.get(viewId);
        View view = viewWeakReference != null ? viewWeakReference.get() : null;
        if (view == null) {
            view = mNavigationBar.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }


    /**
     * 返回 Builder
     *
     * @return
     */
    public B getBuilder() {
        return mBuilder;
    }

    /**
     * Builder 构建类
     * 构建 NavigationBar 和 存储参数
     */
    public static abstract class Builder<B extends Builder> {

        public Context mContext;
        public int mLayout;
        public ViewGroup mParent;
        public Map<Integer, CharSequence> mTextMaps;
        public Map<Integer, View.OnClickListener> mClickListenerMaps;

        public Builder(Context context, int layoutId, ViewGroup viewGroup) {
            this.mContext = context;
            this.mLayout = layoutId;
            this.mParent = viewGroup;
            this.mTextMaps = new HashMap<>();
            this.mClickListenerMaps = new HashMap<>();
        }


        /**
         * 用来创建 NavigationBar
         *
         * @return
         */
        public abstract AbsNavigationBar create();

        /**
         * 返回的是 AbsNavigationBar 的 Builder 需要使用泛型
         * <p>
         * 设置文本
         *
         * @param viewId
         * @param text
         * @return
         */
        public B setText(int viewId, String text) {
            mTextMaps.put(viewId, text);
            return (B) this;
        }

        /**
         * 设置点击事件
         *
         * @param viewId
         * @param clickListener
         * @return
         */
        public B setOnClickListener(int viewId, View.OnClickListener clickListener) {
            mClickListenerMaps.put(viewId, clickListener);
            return (B) this;
        }
    }
}
