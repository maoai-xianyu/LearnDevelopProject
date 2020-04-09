package com.mao.cn.learnDevelopProject.ui.activity.defineview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.dragview.DragBubbleTouchListener;
import com.mao.cn.learnDevelopProject.widgets.dragview.DragBubbleView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * @author zhangkun
 * @time 2020-02-08 15:26
 */
public class QQDragBubbleActivity extends BaseActivity implements DragBubbleTouchListener.BubbleDisappearListener {


    @BindView(R.id.ib_header_back)
    ImageButton ibBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeader;
    @BindView(R.id.tvDrag)
    TextView tvDrag;

    @Override
    public void getArgs(Bundle var1) {

    }

    @Override
    public int setView() {
        return R.layout.aty_define_qq_drag_bubble;
    }

    @Override
    public void initView() {
        ibBack.setVisibility(View.VISIBLE);
        tvHeader.setVisibility(View.VISIBLE);
        tvHeader.setText("可以拖拽");
        DragBubbleView.attach(tvDrag, this);

    }

    @Override
    public void setListener() {

        RxView.clicks(ibBack).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                finish();

            }
        }, throwable -> LogU.e(throwable.getMessage()));

        RxView.clicks(tvDrag).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {

            }
        }, throwable -> LogU.e(throwable.getMessage()));

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

    @Override
    public void dismiss(View view) {

    }
}
