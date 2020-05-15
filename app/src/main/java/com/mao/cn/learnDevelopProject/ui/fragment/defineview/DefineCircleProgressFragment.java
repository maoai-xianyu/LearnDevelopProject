package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.widgets.CircleProgressBar;
import com.mao.cn.learnDevelopProject.widgets.DefineShapeView;
import com.mao.cn.learnDevelopProject.widgets.ProgressBar;
import com.mao.cn.learnDevelopProject.widgets.ShapeView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;

public class DefineCircleProgressFragment extends BaseFragment {


    @BindView(R.id.circleProgressBar)
    CircleProgressBar mQQStepView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.defineShapeView)
    DefineShapeView defineShapeView;
    @BindView(R.id.shapeView)
    ShapeView shapeView;

    public static DefineCircleProgressFragment newInstance() {
        DefineCircleProgressFragment fragment = new DefineCircleProgressFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_cicle_progress;
    }

    @Override
    public void initView() {

        mQQStepView.setMaxPresent(100);
        progressBar.setMax(100);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 100);
        // 插值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            mQQStepView.setCurrentPresent((int) animatedValue);
            progressBar.setProgress((int) animatedValue);
        });
        valueAnimator.start();


        Observable.interval(0, 1, TimeUnit.SECONDS)
                .compose(timer())
                .subscribe(aLong -> {
                    defineShapeView.changeShape();

                }, throwable -> LogU.e(throwable.toString()));


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                shapeView.exchange();
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


    }

    @Override
    public void setListener() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
