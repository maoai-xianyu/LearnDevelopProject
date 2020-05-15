package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.os.Bundle;
import android.view.View;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.adapter.define.CommonFlowAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.ToastUtils;
import com.mao.cn.learnDevelopProject.widgets.TagLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DefineTagLayoutViewFragment extends BaseFragment {

    private List<String> mDatas;

    @BindView(R.id.tl)
    TagLayout mTagLayout;

    private CommonFlowAdapter mAdapter;

    public static DefineTagLayoutViewFragment newInstance() {
        DefineTagLayoutViewFragment fragment = new DefineTagLayoutViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_tag_layout;
    }

    @Override
    public void initView() {

        mDatas = new ArrayList<>();
        mDatas.add("2222");
        mDatas.add("4444");
        mDatas.add("4444444");
        mDatas.add("2222");
        mDatas.add("4444");
        mDatas.add("123");
        mDatas.add("test");
        mDatas.add("0 word");
        mDatas.add("2222");
        mDatas.add("hai hai");
        mDatas.add("my gad");
        mDatas.add("2222");
        mAdapter = new CommonFlowAdapter<String>(context, mDatas, R.layout.flow_layout_item) {

            @Override
            public void convert(FlowHolder holder, final String item,
                                final int position) {
                holder.setText(R.id.id_text, item);
                holder.setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.show("item "+item);
                        mDatas.remove(item);
                        notifyDataSetChanged();
                    }
                });
            }
        };

        mTagLayout.setAdapter(mAdapter);

    }

    @Override
    public void setListener() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
