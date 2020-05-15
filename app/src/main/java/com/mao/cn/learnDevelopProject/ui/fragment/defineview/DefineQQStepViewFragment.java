package com.mao.cn.learnDevelopProject.ui.fragment.defineview;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.widgets.QQStepView;

import butterknife.BindView;

public class DefineQQStepViewFragment extends BaseFragment {


    @BindView(R.id.QQStepView)
    QQStepView mQQStepView;

    public static DefineQQStepViewFragment newInstance() {
        DefineQQStepViewFragment fragment = new DefineQQStepViewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_define_qqstepview;
    }

    @Override
    public void initView() {

        /*mQQStepView.setStepMax(20);

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .compose(timer())
                .subscribe(aLong -> mQQStepView.setCurrentStepMax(aLong.intValue()), throwable -> LogU.e(throwable.toString()));*/

        // 属性动画
        mQQStepView.setStepMax(4000);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        // 插值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(animation -> {
            float animatedValue = (float) animation.getAnimatedValue();
            mQQStepView.setCurrentStepMax((int) animatedValue);
        });
        valueAnimator.start();


    }

    @Override
    public void setListener() {


    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }

}
