package com.mao.cn.learnDevelopProject.ui.fragment;

import android.os.Bundle;

import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.component.AppComponent;
import com.mao.cn.learnDevelopProject.ui.commons.BaseFragment;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

import static com.mao.cn.learnDevelopProject.ui.funcitonMethod.RxJavaMethodFunc.applyIoSchedulers;

public class NewFragment extends BaseFragment {


    private CompositeSubscription compositeSubscriptionVip;
    private long[] timesStampArray = new long[1];

    public static NewFragment newInstance() {
        NewFragment fragment = new NewFragment();
        return fragment;
    }


    @Override
    protected void getArgs(Bundle bundle) {

    }

    @Override
    protected int setView() {
        return R.layout.frg_new;
    }

    @Override
    public void initView() {
        compositeSubscriptionVip = new CompositeSubscription();

       /* timePollingVipCourse(timeStart + KeyMaps.PollingTime.VIP_COURSE_TIME, false);*/

    }

    @Override
    public void setListener() {

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

    }


    private void timePollingVipCourse(long times, boolean isStart) {
        Subscription subscribeVipCourse = Observable.interval(30, TimeUnit.SECONDS)
                .flatMap(new Func1<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Long aLong) {
                        timesStampArray[0] = timesStampArray[0] + 30 * 1000L;
                        LogU.d("timeStamp[1] " + timesStampArray[0]);
                        return Observable.just(timesStampArray[0]);
                    }
                })
                .takeUntil(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long aLong) {
                        LogU.d("条件时间 " + times + "更新的时间" + aLong + " 时间是否大于自己的时间 " + (aLong >= times));
                        return aLong >= times;
                    }
                }).compose(applyIoSchedulers()).subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        LogU.d("timeStamp[1] " + " onNext " + aLong);
                        if (isStart) {
                            LogU.d("开始  timeStamp[1] " + aLong + "times " + times + " 时间是否大于自己的时间 " + (aLong >= times));
                            if (aLong >= times) {
                                LogU.d("开始显示上课按钮");
                            }
                        } else {

                            LogU.d("正在进行中  timeStamp[1] " + aLong + "times " + times + " 时间是否大于自己的时间 " + (aLong >= times));
                            if (aLong >= times) {
                                LogU.d("课程结束");
                            }
                        }

                    }
                });

        if (compositeSubscriptionVip != null) {
            compositeSubscriptionVip.add(subscribeVipCourse);
        }
    }
}
