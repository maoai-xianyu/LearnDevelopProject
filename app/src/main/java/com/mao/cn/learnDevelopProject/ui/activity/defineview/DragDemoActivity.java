package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class DragDemoActivity extends BaseActivity {


    @BindView(R.id.ib_header_back)
    ImageButton ibBack;

    @BindView(R.id.lv1)
    ListView mListView;

    private List<String> mItems;


    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_drag;
    }

    @Override
    public void initView() {
        ibBack.setVisibility(View.VISIBLE);

        mItems = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            mItems.add("item - " + i);

        }


        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView= LayoutInflater.from(DragDemoActivity.this).inflate(R.layout.item_lv,parent,false);
                TextView textView = convertView.findViewById(R.id.item_tv);
                textView.setText(mItems.get(position));
                return convertView;
            }
        });
    }

    @Override
    public void setListener() {

        RxView.clicks(ibBack).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                finish();

            }
        }, throwable -> LogU.e(throwable.getMessage()));

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
