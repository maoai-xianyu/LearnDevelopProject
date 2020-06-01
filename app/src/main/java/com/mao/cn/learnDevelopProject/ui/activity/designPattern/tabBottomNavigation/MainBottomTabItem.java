package com.mao.cn.learnDevelopProject.ui.activity.designPattern.tabBottomNavigation;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.mao.cn.learnDevelopProject.R;

/**
 * @author zhangkun
 * @time 2020-06-01 09:40
 * @Description
 */
public class MainBottomTabItem extends BottomTabItem {

    private Builder mBuilder;

    private MainBottomTabItem(Context context) {
        super(context, R.layout.item_tab_view);
    }

    public MainBottomTabItem(Builder builder) {
        this(builder.mContext);
        this.mBuilder = builder;
    }

    @Override
    protected void initLayout() {
        // 初始化显示
        TextView tabText = findViewById(R.id.tvBottom);
        ImageView tabIcon = findViewById(R.id.ivBottom);
        if (!TextUtils.isEmpty(mBuilder.text)) {
            tabText.setText(mBuilder.text);
        }
        if (mBuilder.resIconId != 0) {
            tabIcon.setImageResource(mBuilder.resIconId);
        }

    }

    @Override
    void setSelected(boolean selected) {
        TextView tabText = findViewById(R.id.tvBottom);
        ImageView tabIcon = findViewById(R.id.ivBottom);
        tabText.setSelected(selected);
        tabIcon.setSelected(selected);
    }


    public static class Builder {

        private Context mContext;
        private String text;
        private int resIconId;


        public Builder(Context context) {
            mContext = context;
        }


        public Builder text(String text) {
            this.text = text;
            return this;
        }



        public Builder resIcon(int resIconId) {
            this.resIconId = resIconId;
            return this;
        }


        public MainBottomTabItem create() {
            return new MainBottomTabItem(this);
        }


    }
}
