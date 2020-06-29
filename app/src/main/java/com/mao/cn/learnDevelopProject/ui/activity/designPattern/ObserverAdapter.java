package com.mao.cn.learnDevelopProject.ui.activity.designPattern;

import android.content.Context;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.ui.activity.designPattern.observer.ObserverUserModel;
import com.mao.cn.learnDevelopProject.ui.adapter.mdadapter.CommonRecyclerAdapter;
import com.mao.cn.learnDevelopProject.ui.adapter.mdadapter.ViewHolder;

import java.util.List;

/**
 * @author zhangkun
 * @time 2020-05-25 10:53
 * @Description
 */
public class ObserverAdapter extends CommonRecyclerAdapter<ObserverUserModel> {


    public ObserverAdapter(Context context, List<ObserverUserModel> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ObserverUserModel item, int position) {
        holder.setText(R.id.tv, item.getName());
    }


}
