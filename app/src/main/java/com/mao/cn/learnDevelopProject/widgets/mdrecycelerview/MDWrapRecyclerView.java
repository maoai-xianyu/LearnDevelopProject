package com.mao.cn.learnDevelopProject.widgets.mdrecycelerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * @author zhangkun
 * @time 2020-04-04 18:02
 */
public class MDWrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViewInfos = new ArrayList<>();
    private ArrayList<View> mFooterViewInfos = new ArrayList<>();
    private Adapter mAdapter;

    public MDWrapRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MDWrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MDWrapRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void addHeaderView(View v) {
        mHeaderViewInfos.add(v);
        if (mAdapter != null) {
            if (!(mAdapter instanceof MDHeaderViewListAdapter)) {
                mAdapter = new MDHeaderViewListAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }


    public void addFooterView(View v) {

        mFooterViewInfos.add(v);

        if (mAdapter != null) {
            if (!(mAdapter instanceof MDHeaderViewListAdapter)) {
                mAdapter = new MDHeaderViewListAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }


    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.size() > 0 || mFooterViewInfos.size() > 0) {
            mAdapter = new MDHeaderViewListAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
        requestLayout();
    }

}
