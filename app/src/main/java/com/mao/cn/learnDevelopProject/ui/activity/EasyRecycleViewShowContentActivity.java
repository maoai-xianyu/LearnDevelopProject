// +----------------------------------------------------------------------
// | Project:   LearnMyDevelopProject
// +----------------------------------------------------------------------
// | CreateTime: 08/08/2017 16:39 下午
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.mao.cn.learnDevelopProject.R;
import com.mao.cn.learnDevelopProject.contants.ValueMaps;
import com.mao.cn.learnDevelopProject.di.component.AppComponent;
import com.mao.cn.learnDevelopProject.di.component.DaggerEasyRecycleViewShowContentComponent;
import com.mao.cn.learnDevelopProject.di.modules.EasyRecycleViewShowContentModule;
import com.mao.cn.learnDevelopProject.model.MovieDetail;
import com.mao.cn.learnDevelopProject.ui.adapter.EasyRecycleViewTopAdapter;
import com.mao.cn.learnDevelopProject.ui.commons.BaseActivity;
import com.mao.cn.learnDevelopProject.ui.features.IEasyRecycleViewShowContent;
import com.mao.cn.learnDevelopProject.ui.presenter.EasyRecycleViewShowContentPresenter;
import com.mao.cn.learnDevelopProject.utils.tools.ListU;
import com.mao.cn.learnDevelopProject.utils.tools.LogU;
import com.mao.cn.learnDevelopProject.utils.tools.Util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class EasyRecycleViewShowContentActivity extends BaseActivity implements IEasyRecycleViewShowContent, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    EasyRecycleViewShowContentPresenter presenter;

    @BindView(R.id.ib_header_back)
    ImageButton ibHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.rvData)
    EasyRecyclerView rvData;

    private int page = 0;
    private boolean hasNetWork = true;

    private RecyclerArrayAdapter<MovieDetail> adapter;
    private Handler handler = new Handler();


    @Override
    public void getArgs(Bundle bundle) {

    }

    @Override
    public int setView() {
        return R.layout.aty_easy_recycle_view_show_content;
    }

    @Override
    public void initView() {
        ibHeaderBack.setVisibility(View.VISIBLE);
        tvHeaderTitle.setText("点击模拟 有网络");

        LinearLayoutManager linearLayoutCourse = new LinearLayoutManager(context);
        linearLayoutCourse.setOrientation(LinearLayoutManager.VERTICAL);
        rvData.setLayoutManager(linearLayoutCourse);

        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(this, 1f), Util.dip2px(this, 160), 0);
        itemDecoration.setDrawLastItem(false);
        rvData.addItemDecoration(itemDecoration);
        adapter = new EasyRecycleViewTopAdapter(this);
        rvData.setAdapterWithProgress(adapter);

    }

    @Override
    public void setListener() {
        RxView.clicks(ibHeaderBack).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            finish();
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });


        RxView.clicks(tvHeaderTitle).throttleFirst(ValueMaps.ClickTime.BREAK_TIME_MILLISECOND, TimeUnit
                .MILLISECONDS).subscribe(aVoid -> {
            if (hasNetWork) {
                hasNetWork = false;
                tvHeaderTitle.setText("点击模拟 无网络");
            } else {
                hasNetWork = true;
                tvHeaderTitle.setText("点击模拟 有网络");
            }
        }, throwable -> {
            LogU.e(throwable.getMessage());
        });

        adapter.setMore(R.layout.item_view_more, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                if (!checkActivityState()) return;
                LogU.i("EasyRecyclerView onLoadMore " + page);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新
                        if (!hasNetWork) {
                            adapter.pauseMore();
                            return;
                        }
                        presenter.getMovieTop(page, 10);
                        page++;
                    }
                }, 2000);
            }

            @Override
            public void onMoreClick() {

            }
        });

        adapter.setNoMore(R.layout.item_view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });


        adapter.setError(R.layout.item_view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {
                adapter.resumeMore();
                LogU.i("EasyRecyclerView onErrorShow ");
            }

            @Override
            public void onErrorClick() {
                adapter.resumeMore();
                LogU.i("EasyRecyclerView onErrorClick ");
            }
        });

        rvData.setRefreshListener(this);

        onRefresh();

    }

    @Override
    protected void setupComponent(AppComponent appComponent) {

        DaggerEasyRecycleViewShowContentComponent
                .builder().appComponent(appComponent)
                .easyRecycleViewShowContentModule(new EasyRecycleViewShowContentModule(this))
                .build().inject(this);

    }

    @Override
    public void showTopMovie(List<MovieDetail> subjects, String title) {
        if (!checkActivityState()) return;
        if (ListU.notEmpty(subjects)) {
            if (page == 1){
                adapter.clear();
            }
            adapter.addAll(subjects);
        } else {
            rvData.showEmpty();
        }
    }

    @Override
    public void showErrorMovie() {
        if (!checkActivityState()) return;
        rvData.showError();
    }

    @Override
    public void onRefresh() {
        if (!checkActivityState()) return;
        page = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //adapter.clear();
                //刷新
                if (!hasNetWork) {
                    adapter.pauseMore();
                    return;
                }
                presenter.getMovieTop(page, 10);
                page = 1;
            }
        }, 2000);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
