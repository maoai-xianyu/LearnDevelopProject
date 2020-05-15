package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.adapter.mdadapter.CommonRecyclerAdapter;
import com.mao.cn.learnDevelopProject.ui.adapter.mdadapter.MultiTypeSupport;
import com.mao.cn.learnDevelopProject.ui.adapter.mdadapter.ViewHolder;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecyclerViewAdapterFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;


    public static RecyclerViewAdapterFragment newInstance() {
        RecyclerViewAdapterFragment fragment = new RecyclerViewAdapterFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_recyclerview_adapter;
    }

    @Override
    public void initView() {

        List<Msg> msgList = new ArrayList<>();
        Msg msg;
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                msg = new Msg("自己的消息" + i, 1);
                msgList.add(msg);
            } else {
                msg = new Msg("他人的消息" + i, 0);
                msgList.add(msg);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rv.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(context, msgList);
        rv.setAdapter(adapter);

    }


    class Msg {
        private String msg;
        private int isMe;

        public Msg(String msg, int isMe) {
            this.msg = msg;
            this.isMe = isMe;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getIsMe() {
            return isMe;
        }

        public void setIsMe(int isMe) {
            this.isMe = isMe;
        }
    }


    class MyAdapter extends CommonRecyclerAdapter<Msg> {

        public MyAdapter(Context context, List<Msg> data) {
            super(context, data, new MultiTypeSupport<Msg>() {
                @Override
                public int getLayoutId(Msg item, int position) {
                    return item.getIsMe() == 1 ? R.layout.adapter_me : R.layout.adapter_other;
                }
            });
        }

        @Override
        public void convert(ViewHolder holder, Msg item, int position) {
            holder.setText(R.id.chat_me, item.msg);
        }

    }


    @Override
    public void setListener() {


    }


    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
