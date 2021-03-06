// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/16/2017 15:27 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxAdapterView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.di.component.DaggerRxjavaLearnRxBingdingComponent;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.modules.RxjavaLearnRxBingdingModule;
import com.mao.cn.learnDevelopProject.ui.adapter.RxJavaRcStringAdapter;
import com.mao.cn.learnDevelopProject.ui.adapter.RxListStringAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IRxjavaLearnRxBingding;
import com.mao.cn.learnDevelopProject.ui.funcitonMethod.InitDataMethodFunc;
import com.mao.cn.learnDevelopProject.ui.presenter.RxjavaLearnRxBingdingPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class RxjavaLearnRxBingdingActivity extends BaseActivity implements IRxjavaLearnRxBingding {

    @Inject
    RxjavaLearnRxBingdingPresenter presenter;
    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.e_mail)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @BindView(R.id.et_list_view)
    EditText etListView;
    @BindView(R.id.ls_data)
    ListView lsData;
    @BindView(R.id.et_recycler_view)
    EditText etRecyclerView;
    @BindView(R.id.rv_data)
    RecyclerView rvData;
    private RxListStringAdapter arrayAdapter;
    private List<String> strings;
    private RxJavaRcStringAdapter rcStringAdapter;

    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_rxjava_learn_rxbingding;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText(getString(R.string.header_rxjava_binding));

        arrayAdapter = new RxListStringAdapter(this);
        strings = InitDataMethodFunc.initStringData();
        arrayAdapter.addStringList(strings);
        lsData.setAdapter(arrayAdapter);

        LinearLayoutManager linearLayoutCourse = new LinearLayoutManager(context);
        linearLayoutCourse.setOrientation(LinearLayoutManager.VERTICAL);
        rvData.setLayoutManager(linearLayoutCourse);
        rcStringAdapter = new RxJavaRcStringAdapter(this);
        rcStringAdapter.addStringList(strings);
        rvData.setAdapter(rcStringAdapter);
    }

    @Override
    public void setListener() {

        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.getMessage()));

        RxView.longClicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> finish(), throwable -> LogU.e(throwable.getMessage()));

        RxAdapterView.itemClicks(lsData)
                .subscribe(integer -> Toast.makeText(RxjavaLearnRxBingdingActivity.this, "item click " + integer + " ==值 " + strings.get(integer), Toast.LENGTH_SHORT).show());

        RxAdapterView.itemLongClicks(lsData)
                .subscribe(integer -> Toast.makeText(RxjavaLearnRxBingdingActivity.this, "item long click " + integer + " ==值 " + strings.get(integer), Toast.LENGTH_SHORT).show());

        RxTextView.textChanges(etListView)
                .debounce(600, TimeUnit.MILLISECONDS)
                .map(charSequence -> {
                    //get the keyword
                    String key = charSequence.toString();
                    return key;
                })

                //
                .switchMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.just(s);
                    }
                })
                .observeOn(Schedulers.io())
                .map(keyWord -> {
                    //get list
                    List<String> dataList = new ArrayList<>();
                    if (!TextUtils.isEmpty(keyWord)) {
                        for (String s : strings) {
                            if (s != null) {
                                if (s.contains(keyWord)) {
                                    dataList.add(s);
                                }
                            }
                        }
                    } else {
                        dataList.addAll(strings);
                    }
                    return dataList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings1 -> {

                    arrayAdapter.clear();
                    arrayAdapter.addStringList(strings1);
                    arrayAdapter.notifyDataSetChanged();

                });


        RxRecyclerView.scrollStateChanges(rvData).subscribe(integer -> LogU.i(" integer " + integer));

        RxRecyclerView.childAttachStateChangeEvents(rvData)
                .subscribe(recyclerViewChildAttachStateChangeEvent -> LogU.i(" recyclerViewChildAttachStateChangeEvent " + recyclerViewChildAttachStateChangeEvent.child()));

        RxRecyclerView.scrollEvents(rvData).subscribe(recyclerViewScrollEvent -> LogU.i(" recyclerViewScrollEvent " + recyclerViewScrollEvent));

        /*RxRecyclerViewAdapter.dataChanges(rcStringAdapter).subscribe(new Action1<RxJavaRcStringAdapter>() {
            @Override
            public void call(RxJavaRcStringAdapter rxJavaRcStringAdapter) {

            }
        });*/

        RxTextView.textChanges(etRecyclerView)
                .debounce(600, TimeUnit.MILLISECONDS)
                .map(charSequence -> {
                    //get the keyword
                    String key = charSequence.toString();
                    return key;
                })
                .observeOn(Schedulers.io())
                .map(keyWord -> {
                    //get list
                    List<String> dataList = new ArrayList<>();
                    if (!TextUtils.isEmpty(keyWord)) {
                        for (String s : strings) {
                            if (s != null) {
                                if (s.contains(keyWord)) {
                                    dataList.add(s);
                                }
                            }
                        }
                    } else {
                        dataList.addAll(strings);
                    }
                    return dataList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strings1 -> {
                    rcStringAdapter.clear();
                    rcStringAdapter.addStringList(strings1);
                });


        Observable<String> mapEtMail = RxTextView.textChanges(etEmail).map(CharSequence::toString);
        Observable<String> observablePassword = RxTextView.textChanges(etPwd).map(CharSequence::toString);


        Observable.combineLatest(mapEtMail, observablePassword, new Func2<String, String, Boolean>() {
            @Override
            public Boolean call(String email, String password) {
                return isEmailValid(email) && isPasswordValid(password);
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean){
                    btnConfirm.setVisibility(View.VISIBLE);
                }else {
                    btnConfirm.setVisibility(View.GONE);
                }
            }
        });


    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerRxjavaLearnRxBingdingComponent.builder()
                .appComponent(appComponent)
                .rxjavaLearnRxBingdingModule(new RxjavaLearnRxBingdingModule(this))
                .build().inject(this);
    }

}
